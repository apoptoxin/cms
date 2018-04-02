package com.micronil.web.service;

import com.micronil.web.entity.*;
import com.micronil.web.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by apoptoxin on 2018/3/30.
 */
@Service
public class PrivilegeService {

    @Autowired
    private ModulePrivilegeRepository modulePrivilegeRepository;

    @Autowired
    private RoleModulePrivilegeRepository roleModulePrivilegeRepository;

    @Autowired
    private UserRoleRepository userRoleRepository;

    @Autowired
    private ModuleRepository moduleRepository;

    @Autowired
    private PrivilegeRepository privilegeRepository;

    public List<String> allUrlsForUser(User user) {
        List<UserRole> roles = userRoleRepository.findByUser(user);
        if (!(roles instanceof List)) {
            return new ArrayList<>();
        }
        List<String> list = new ArrayList<>();
        for (UserRole userRole : roles) {
            List<RoleModulePrivilege> modulePrivileges = roleModulePrivilegeRepository.findAllByRole(userRole.getRole());
            if (modulePrivileges instanceof  List) {
                for (RoleModulePrivilege modulePrivilege : modulePrivileges) {
                    list.add(modulePrivilege.getModulePrivilege().getModule().getUrl());
                }
            }
        }
        return list;
    }

    public boolean userHasPrivilegeForModuleURLAndCode(User user, String url, String privilegeCode) {
        List<UserRole> roles = userRoleRepository.findByUser(user);
        if (!(roles instanceof List)) {
            return false;
        }
        boolean result = false;
        Module module = moduleRepository.findOneByUrl(url);
        Privilege privilege = privilegeRepository.findOneByPrivilegeCode(privilegeCode);

        if (!(module instanceof  Module) || !(privilege instanceof Privilege)) {
            //没有该模块或者没有该权限认为不需要鉴权,所以返回true
            return true;
        }
        ModulePrivilege modulePrivilege = modulePrivilegeRepository.findOneByModuleAndPrivilege(module,privilege);
        if (!(modulePrivilege instanceof  ModulePrivilege)) {
            return true;
        }
        for (UserRole userRole : roles) {
            if (userRole.getRole().getLevel() == 0) {
                //认为是根用户,完全放开权限
                return true;
            }
            result = roleModulePrivilegeRepository.existsByRoleAndModulePrivilege(userRole.getRole(),modulePrivilege);
            if (result) {
                break;
            }
        }
        return result;
    }
}
