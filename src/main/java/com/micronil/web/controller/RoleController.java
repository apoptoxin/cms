package com.micronil.web.controller;

import com.micronil.utils.CookieInterpreter;
import com.micronil.utils.PrivilegeFilterComponent;
import com.micronil.web.entity.Role;
import com.micronil.web.entity.User;
import com.micronil.web.service.RoleService;
import com.micronil.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * Created by apoptoxin on 2018/3/28.
 */
@RestController
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private UserService userService;

    @Autowired
    private PrivilegeFilterComponent privilegeFilterComponent;

    @RequestMapping("/addrole")
    public String addRole(@CookieValue(name="accesstoken",defaultValue = "")String accessToken, String roleValue, String parentId, Map<String,Object> map) {
        User user = userService.findUser(CookieInterpreter.parseUserNameFromCookie(accessToken), CookieInterpreter.parseMD5PasswordFromCookie(accessToken));
        map.put("accesssUrls", privilegeFilterComponent.findAllUrlsWithUser(user));
        Role role = new Role();
        role.setRoleName(roleValue);
        roleService.save(role,Long.parseLong(parentId));
        return "";
    }

    @RequestMapping("/findsub")
    public String findSub(String id) {
        List<Role> list = roleService.findAllParentNodeByRole(roleService.findRoleById(Long.parseLong(id)));
        String result = "";
        for (Role role : list) {
            result = result + role.getRoleName() + " ";
        }
        return result;
    }
}
