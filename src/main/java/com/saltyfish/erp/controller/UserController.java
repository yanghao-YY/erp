package com.saltyfish.erp.controller;

import com.alibaba.fastjson.JSON;
import com.saltyfish.erp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import com.saltyfish.erp.util.Result;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class UserController {
    @Autowired
    UserService userService;
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestParam("username")String username, @RequestParam("password")String password, HttpSession session,HttpServletResponse response){
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
}
