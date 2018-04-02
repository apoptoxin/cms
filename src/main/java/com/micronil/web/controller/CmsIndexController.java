package com.micronil.web.controller;

import com.micronil.utils.CookieInterpreter;
import com.micronil.utils.PrivilegeFilterComponent;
import com.micronil.utils.UserConvertComponent;
import com.micronil.web.entity.User;
import com.micronil.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by apoptoxin on 2018/3/27.
 */
@Controller
public class CmsIndexController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserConvertComponent userConvertComponent;
    @Autowired
    private PrivilegeFilterComponent privilegeFilterComponent;

    @RequestMapping("/index")
    public String index(@CookieValue(name="accesstoken",defaultValue = "")String accesstoken, ModelMap map) {
//        User user = userService.findUser(CookieInterpreter.parseUserNameFromCookie(accesstoken), CookieInterpreter.parseMD5PasswordFromCookie(accesstoken));
//        if (user instanceof User) {
//            map.put("curUser", user);
//        }
        return "forward:/userbook";
    }

    @RequestMapping("/noprivilege")
    public String noPrivilege(@CookieValue(name="accesstoken",defaultValue = "")String accessToken, ModelMap map) {
        userConvertComponent.parseAccessTokenAndSetIntoMap(accessToken,map);
        User user = userService.findUser(CookieInterpreter.parseUserNameFromCookie(accessToken), CookieInterpreter.parseMD5PasswordFromCookie(accessToken));
        map.put("accesssUrls", privilegeFilterComponent.findAllUrlsWithUser(user));
        return "noprivilege";
    }
}
