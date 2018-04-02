package com.micronil.web.repository;

import com.micronil.web.entity.Module;
import com.micronil.web.entity.ModulePrivilege;
import com.micronil.web.entity.Privilege;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by apoptoxin on 2018/3/30.
 */
public interface ModulePrivilegeRepository extends CrudRepository<ModulePrivilege, Long> {
    public ModulePrivilege findOneByModuleAndPrivilege(Module module, Privilege privilege);
}
