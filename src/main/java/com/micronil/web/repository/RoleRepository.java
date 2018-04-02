package com.micronil.web.repository;

import com.micronil.web.entity.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

/**
 * Created by apoptoxin on 2018/3/28.
 */
public interface RoleRepository extends CrudRepository<Role,Long> {

    public List<Role> findAllSubNodeByKeyLike(String key);

    public List<Role> findByIdIn(Collection<Long> list);

}
