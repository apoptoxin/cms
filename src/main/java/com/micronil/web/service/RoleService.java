package com.micronil.web.service;

import com.micronil.web.entity.Role;
import com.micronil.web.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
 * Created by apoptoxin on 2018/3/28.
 */
@Service
@Transactional
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public void save(Role role, Long parentRoleId) {
        Optional<Role> opRole = roleRepository.findById(parentRoleId);
        Role queryRole = null;
        if (opRole.isPresent()) {
            queryRole = opRole.get();
        } else {
            queryRole = null;
        }
        save(role, queryRole);
    }

    public void save(Role role, Role parentRole) {
        if (parentRole == null) {
            role.setKey("");
            role.setLevel(1);
            role.setParentRoleId(Long.parseLong("0"));
            role.setReverseKey("");
        } else {
            role.setKey(parentRole.getKey()+parentRole.getId()+"-");
            role.setLevel(parentRole.getLevel()+1);
            role.setParentRoleId(parentRole.getId());
            role.setReverseKey(parentRole.getKey()+parentRole.getId());
        }
        roleRepository.save(role);
    }

    public Role findRoleById(Long roleId) {
        Optional<Role> opRole = roleRepository.findById(roleId);
        return opRole.isPresent()?opRole.get():null;
    }

    public List<Role> findAllSubnodeByRole(Role role) {
        return roleRepository.findAllSubNodeByKeyLike(role.getKey()+"-"+role.getId()+"-%");
    }

    public List<Role> findAllParentNodeByRole(Role role) {
        if (role == null) {
            return new ArrayList<>();
        }
        Optional<Role> optional = roleRepository.findById(role.getId());
        Role queryRole = optional.isPresent() ? optional.get() : null;
        String[] array = queryRole.getReverseKey().split("-");
        Collection<Long> collection = new HashSet<>();
        for (int i = 0; i < array.length ; i++) {
            if (array[i].length() > 0) {
                collection.add(Long.parseLong(array[i]));
            }
        }
        return roleRepository.findByIdIn(collection);
    }
}
