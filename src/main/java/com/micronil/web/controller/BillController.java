package com.micronil.web.controller;

import com.micronil.utils.CookieInterpreter;
import com.micronil.utils.PrivilegeFilterComponent;
import com.micronil.utils.UserConvertComponent;
import com.micronil.web.entity.Bill;
import com.micronil.web.entity.User;
import com.micronil.web.service.BillService;
import com.micronil.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by apoptoxin on 2018/3/29.
 */
@Controller
public class BillController {

    @Autowired
    private BillService billService;

    @Autowired
    private UserService userService;

    @Autowired
    private UserConvertComponent userConvertComponent;

    @Autowired
    private PrivilegeFilterComponent privilegeFilterComponent;

    @RequestMapping("/userbook")
    public String userBook(@CookieValue(name="accesstoken",defaultValue = "")String accessToken, @RequestParam(value = "daterange", required = false, defaultValue = "")String dateRange, ModelMap map) {
        userConvertComponent.parseAccessTokenAndSetIntoMap(accessToken,map);
        User user = userService.findUser(CookieInterpreter.parseUserNameFromCookie(accessToken), CookieInterpreter.parseMD5PasswordFromCookie(accessToken));
        map.put("accesssUrls", privilegeFilterComponent.findAllUrlsWithUser(user));
        List<Bill> list = new ArrayList<>();
        if (dateRange.length() <= 0) {
            list = billService.findAllByUser(user);
        } else {
            String[] dates = dateRange.split("-");
            if (dates.length == 2){
                String startString = dates[0];
                String endString = dates[1];
                DateFormat format = new SimpleDateFormat("MM/dd/yyyy");
                Date startDate = null;
                Date endDate = null;
                try {
                    startDate = format.parse(startString);
                    endDate = format.parse(endString);
                } catch (ParseException e) {
                    e.printStackTrace();
                }
                if (startDate instanceof Date && endDate instanceof Date) {
                    list = billService.findAllByUserAndTime(user,startDate,new Date(endDate.getTime() + 86400000));
                } else {
                    list = new ArrayList<>();
                }
            } else {
                list = new ArrayList<>();
            }
        }
        if (list instanceof List) {
            map.put("billlist",list);
        } else {
            map.put("billlist", new ArrayList<>());
        }
        return "userbook";
    }
}
