package com.micronil;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by apoptoxin on 2018/3/27.
 */
@Controller
public class IndexController {
    @RequestMapping("/")
    public String index(@CookieValue(name="userName",defaultValue = "")String userName,@CookieValue(name="password",defaultValue = "")String password,ModelMap map) {
        return "redirect:/index";
    }
}
