package com.micronil.web.repository;

import com.micronil.web.entity.Privilege;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by apoptoxin on 2018/3/30.
 */
public interface PrivilegeRepository extends CrudRepository<Privilege,Long> {
    public Privilege findOneByPrivilegeCode(String string);
}
