package com.micronil.web.controller;

import com.micronil.utils.CookieInterpreter;
import com.micronil.utils.PrivilegeFilterComponent;
import com.micronil.web.entity.Module;
import com.micronil.web.entity.User;
import com.micronil.web.service.ModuleService;
import com.micronil.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by apoptoxin on 2018/3/29.
 */
@RestController
public class ModulePrivilegeController {
    @Autowired
    private UserService userService;

    @Autowired
    private PrivilegeFilterComponent privilegeFilterComponent;

    @Autowired
    private ModuleService moduleService;
    @RequestMapping("/addmodule")
    public String addModule(@CookieValue(name="accesstoken",defaultValue = "")String accessToken, String moduleValue, String url, String parentId, Map<String,Object> map) {
        User user = userService.findUser(CookieInterpreter.parseUserNameFromCookie(accessToken), CookieInterpreter.parseMD5PasswordFromCookie(accessToken));
        map.put("accesssUrls", privilegeFilterComponent.findAllUrlsWithUser(user));
        Module module = new Module();
        module.setModuleName(moduleValue);
        module.setUrl(url);
        Module result = moduleService.save(module,Long.parseLong(parentId));
        return "";
    }
}
