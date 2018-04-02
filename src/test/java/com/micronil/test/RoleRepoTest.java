package com.micronil.test;

import com.micronil.web.entity.Role;
import com.micronil.web.service.RoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * Created by apoptoxin on 2018/3/28.
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@EnableAutoConfiguration
public class RoleRepoTest {
    @Autowired
    private RoleService roleService;

    @Test
    public void saveRole() {
        Role newrole = new Role();
        newrole.setRoleName("a");
        roleService.save(newrole,Long.parseLong("-1"));
    }
}
