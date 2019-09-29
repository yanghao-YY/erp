package com.saltyfish.erp.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object verify = request.getSession().getAttribute("username");
        if(verify == null){
            response.sendRedirect("index.html");
            return false;
        }

        return true;
    }
}
