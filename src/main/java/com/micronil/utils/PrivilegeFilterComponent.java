package com.micronil.utils;

import com.micronil.web.entity.User;
import com.micronil.web.service.PrivilegeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by apoptoxin on 2018/3/30.
 */
@Component
public class PrivilegeFilterComponent {
    @Autowired
    PrivilegeService privilegeService;

    public List<String> findAllUrlsWithUser(User user) {
        return privilegeService.allUrlsForUser(user);
    }
}
