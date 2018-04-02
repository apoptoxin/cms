package com.micronil.web.controller;

import com.micronil.utils.CookieInterpreter;
import com.micronil.utils.EncryptCoder;
import com.micronil.utils.PrivilegeFilterComponent;
import com.micronil.utils.UserConvertComponent;
import com.micronil.web.entity.User;
import com.micronil.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by apoptoxin on 2018/3/29.
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private UserConvertComponent userConvertComponent;

    @Autowired
    private PrivilegeFilterComponent privilegeFilterComponent;

    @RequestMapping("/register")
    public String register(){
        return "register";
    }
    @RequestMapping("/adduser")
    public String adduser(HttpServletRequest request, HttpServletResponse response, String userName, String password, String email, ModelMap map) {
        User genUser = new User(userName, EncryptCoder.md5Encrypt(password),email);
        userService.save(genUser);
        if (userService.findUser(userName,EncryptCoder.md5Encrypt(password)) != null) {
            Cookie cookie = CookieInterpreter.parseCookie(userName,EncryptCoder.md5Encrypt(password));
            response.addCookie(cookie);
            return "redirect:index";
        } else {
            map.put("msg","用户名或者密码错误");
            return "login";
        }
    }

    @RequestMapping("/usercenter")
    public String selfInfo(@CookieValue(name="accesstoken",defaultValue = "")String accessToken, ModelMap map) {
        userConvertComponent.parseAccessTokenAndSetIntoMap(accessToken,map);
        User user = userService.findUser(CookieInterpreter.parseUserNameFromCookie(accessToken), CookieInterpreter.parseMD5PasswordFromCookie(accessToken));
        map.put("accesssUrls", privilegeFilterComponent.findAllUrlsWithUser(user));
        return "usercenter";
    }
}
