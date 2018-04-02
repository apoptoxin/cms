package com.micronil.web.controller;

import com.micronil.utils.CookieInterpreter;
import com.micronil.utils.EncryptCoder;
import com.micronil.web.entity.User;
import com.micronil.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by apoptoxin on 2018/3/28.
 */
@Controller
public class LoginController {
    @Autowired
    private UserService userService;

    @RequestMapping("/login")
    public String loginResult(@CookieValue(name="userName",defaultValue = "")String userName, @CookieValue(name="password",defaultValue = "")String password, ModelMap map) {
        return "login";
    }

    @RequestMapping(value = "/actionLogin", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response, String userName, String password, ModelMap map) {
        User user = userService.findUser(userName, EncryptCoder.md5Encrypt(password));
        if (user == null) {
            map.put("msg", "用户名或者密码错误");
            return "login";
        } else {
            Cookie cookie = CookieInterpreter.parseCookie(userName,EncryptCoder.md5Encrypt(password));
            response.addCookie(cookie);
            return "redirect:index";
        }
    }

    @RequestMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response, String userName, String password, String email, ModelMap map) {
        Cookie cookie = new Cookie("accesstoken",null);
        cookie.setMaxAge(0);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:login";
    }
}
