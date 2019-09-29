package com.saltyfish.erp.service;

import com.saltyfish.erp.dao.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImpl implements UserService{
    @Autowired
    UserMapper userMapper;
    @Override
    public int login(String username, String password) {
      int count =   userMapper.login(username,password);
        return count;
    }
}
