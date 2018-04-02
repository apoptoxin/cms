package com.micronil.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

/**
 * Created by apoptoxin on 2018/3/27.
 */
@Controller
public class HelloController {
    @RequestMapping("/hello")
    public String from(Map<String,Object> map) {
        map.put("name", "[Angel -- 守护天使]");
        return "hello";
    }
}
