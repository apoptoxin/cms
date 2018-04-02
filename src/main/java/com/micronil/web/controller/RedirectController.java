package com.micronil.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by apoptoxin on 2018/3/28.
 */
@Controller
public class RedirectController {
    @RequestMapping("/redirect/login")
    public String login(@CookieValue(name="userName",defaultValue = "")String userName, @CookieValue(name="password",defaultValue = "")String password, ModelMap map) {
        map.put("msg","");
        return "forward:/login";
    }
}
