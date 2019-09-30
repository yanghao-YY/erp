package com.saltyfish.erp.controller;

import com.alibaba.fastjson.JSON;
import com.saltyfish.erp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.servlet.server.Session;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import com.saltyfish.erp.util.Result;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam("username")String username, @RequestParam("password")String password, HttpSession session){
       try {
           int verify = userService.login(username,password);
           if( verify == 1){
               session.setAttribute("username",username);
//               response.sendRedirect("index.html");
              return JSON.toJSONString(new Result("0000","登录成功",null));
//                 return "redirect:/index.html";

           }else {
               return JSON.toJSONString(new Result("0001","登录失败",null));
           }
       }catch (Exception e){
           return JSON.toJSONString(new Result("9999","系统异常，请稍后再试！",null));
       }

    }
    @GetMapping("/logout")
    public void logout(HttpServletRequest request,HttpServletResponse response){
        try {
            HttpSession session = request.getSession();
            session.invalidate();
            response.sendRedirect("login.html");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
