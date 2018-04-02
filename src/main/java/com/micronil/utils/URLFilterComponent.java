package com.micronil.utils;

import com.micronil.web.service.ModuleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by apoptoxin on 2018/3/29.
 */
@Component
public class URLFilterComponent {
    public static final long expireTime = 300 * 1000; //3000s

    private List<String> urls;

    private long timeStamp;

    @Autowired
    private ModuleService moduleService;

    public List<String> getUrls() {
        synchronized (URLFilterComponent.class) {
            if (isExpired()) {
                List<String> dburls = moduleService.findAllUrls();
                List<String> cururls =  dburls == null ? new ArrayList<>() : new ArrayList<>(dburls);
                cururls.add("/index");
                timeStamp = new Date().getTime();
                urls = cururls;
            }
            return urls;
        }
    }

    private boolean isExpired() {
        return new Date().getTime() - timeStamp > expireTime;
    }

    public List<Pattern> getUrlPatterns() {
        List<Pattern> patterns = new ArrayList<>();
        List<String> urls  = getUrls();
        for (String url : urls) {
            patterns.add(Pattern.compile(url));
        }
        return patterns;
    }

    /**
     * 是否需要过滤
     * @param url
     * @return
     */
    public boolean isInclude(String url) {
        List<Pattern> patterns = getUrlPatterns();
        for (Pattern pattern : patterns) {
            Matcher matcher = pattern.matcher(url);
            if (matcher.matches()) {
                return true;
            }
        }
        return false;
    }
}
