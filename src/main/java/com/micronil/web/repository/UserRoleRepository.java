package com.micronil.web.repository;

import com.micronil.web.entity.User;
import com.micronil.web.entity.UserRole;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * Created by apoptoxin on 2018/3/30.
 */
public interface UserRoleRepository extends CrudRepository<UserRole, Long> {
    public List<UserRole> findByUser(User user);
}
