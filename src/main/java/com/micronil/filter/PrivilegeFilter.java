package com.micronil.filter;

import com.micronil.utils.CookieInterpreter;
import com.micronil.utils.URLFilterComponent;
import com.micronil.web.entity.User;
import com.micronil.web.service.PrivilegeService;
import com.micronil.web.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.Order;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by apoptoxin on 2018/3/29.
 */
@Order(2)
@WebFilter(filterName = "privilegeFilter", urlPatterns = "/*")
public class PrivilegeFilter implements Filter {

    @Autowired
    private URLFilterComponent urlFilterComponent;

    @Autowired
    private UserService userService;

    @Autowired
    private PrivilegeService privilegeService;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;
        Cookie[] cookies = request.getCookies();
        String userName = "";
        String password = "";
        String url = request.getRequestURI();
        if (urlFilterComponent.isInclude(url)) {
            if (cookies != null) {
                for (Cookie ck : cookies) {
                    if ("accesstoken".equals(ck.getName())) {
                        String value = ck.getValue();
                        if (value == null || value.length()<=0) {
                            response.sendRedirect(request.getContextPath()+"/noprivilege");
                            return;
                        }
                        userName = CookieInterpreter.parseUserNameFromCookie(ck.getValue());
                        password = CookieInterpreter.parseMD5PasswordFromCookie(ck.getValue());
                    }

                }
            }
            if (userName.length() == 0 || password.length() == 0) {
                response.sendRedirect(request.getContextPath()+"/noprivilege");
                return;
            }
            User user = userService.findUser(userName,password);

            if (user == null || !privilegeService.userHasPrivilegeForModuleURLAndCode(user,url,"URL_ALL")) {
                response.sendRedirect(request.getContextPath()+"/noprivilege");
                return;
            }
        }
        filterChain.doFilter(request, servletResponse);
    }

    @Override
    public void destroy() {

    }
}
