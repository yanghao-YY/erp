package com.saltyfish.erp.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        Object verify = request.getSession().getAttribute("username");
        if(verify == null){
            System.out.println("拦截了");
            response.sendRedirect("login.html");
            return false;
        }

        return true;
    }
}
