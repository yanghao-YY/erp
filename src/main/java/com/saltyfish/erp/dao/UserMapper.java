package com.saltyfish.erp.dao;


import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

@Mapper
public interface UserMapper {
    //登录
    @Select("select count(*) from users where username=#{username} and password=#{password}")
    int login(@Param("username")String username,@Param("password")String password);
}
