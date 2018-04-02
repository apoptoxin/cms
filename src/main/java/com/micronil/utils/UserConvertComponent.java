package com.micronil.utils;

import com.micronil.web.entity.User;
import com.micronil.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Created by apoptoxin on 2018/3/29.
 */
@Component
public class UserConvertComponent {
    @Autowired
    private UserService userService;

    public void parseAccessTokenAndSetIntoMap(String accesstoken, Map map) {
        if (accesstoken.length() <= 0) {
            map.put("curUser", new User());
        } else {
            User user = userService.findUser(CookieInterpreter.parseUserNameFromCookie(accesstoken), CookieInterpreter.parseMD5PasswordFromCookie(accesstoken));
            if (user instanceof User) {
                map.put("curUser", user);
            } else {
                map.put("curUser", new User());
            }
        }
    }
}
