package com.micronil.utils;

import org.json.JSONObject;

import javax.servlet.http.Cookie;

/**
 * Created by apoptoxin on 2018/3/29.
 */
public class CookieInterpreter {

    private static final int maxAge = 60 * 60 * 24 *30;//30天

    public static Cookie parseCookie(String userName, String password) {
        String encryptString = "";
        JSONObject object = new JSONObject();
        object.put("userName",userName);
        object.put("password",password);
        String strBeforeEncrypt = object.toString();
        try {
            encryptString = EncryptCoder.aesEncrypt(strBeforeEncrypt);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Cookie cookie = new Cookie("accesstoken", encryptString);
        cookie.setMaxAge(maxAge);
        cookie.setPath("/");
        return cookie;
    }

    public static String parseUserNameFromCookie(String cookieStr) {
        JSONObject object = objectFromEncryptString(cookieStr);
        return object.getString("userName");
    }

    public static String parseMD5PasswordFromCookie(String cookieStr) {
        JSONObject object = objectFromEncryptString(cookieStr);
        return object.getString("password");
    }

    private static JSONObject objectFromEncryptString(String string) {
        JSONObject object = new JSONObject();
        try {
            object = new JSONObject(EncryptCoder.aesDecrypt(string));
        } catch (Exception e) {
            e.printStackTrace();
        }
        return object;
    }

    public static void main(String[] args) throws Exception {
        CookieInterpreter.parseCookie("apoptoxin","123456");
    }
}
