package com.micronil.web.repository;

import com.micronil.web.entity.*;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by apoptoxin on 2018/3/30.
 */
public interface RoleModulePrivilegeRepository extends CrudRepository<RoleModulePrivilege,Long> {
    public boolean existsByRoleAndModulePrivilege(Role role, ModulePrivilege modulePrivilege);
    public List<RoleModulePrivilege> findAllByRole(Role role);
}
