package com.micronil.test;

import com.micronil.web.entity.User;
import com.micronil.web.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by apoptoxin on 2018/3/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRepoTest {
    @Autowired
    private UserService userService;

    @Test
    public void saveUser(){
        User user = new User();
        user.setUserName("apoptoxin");
        user.setPassword("123456");
        user.setEmail("921776860@qq.com");
        userService.save(user);
    }

    @Test
    public void findUserByQuery(){
        User user = userService.findUser("apoptoxin","123456");
        System.out.println(user.getEmail());
    }
}
