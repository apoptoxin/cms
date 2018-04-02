package com.micronil.web.repository;

import com.micronil.web.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by apoptoxin on 2018/3/28.
 */
public interface UserRepository extends CrudRepository<User,Long> {
    @Query("select bean from User bean where bean.userName = ?1 and bean.password = ?2")
    public User findByUserNameAndPassword(String username, String password);
}
