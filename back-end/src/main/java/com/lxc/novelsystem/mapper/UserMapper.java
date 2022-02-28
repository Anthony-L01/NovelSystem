package com.lxc.novelsystem.mapper;


import com.lxc.novelsystem.entity.User;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;


@Mapper
public interface UserMapper {
    /*
    登录时调用

    通过用户号码和密码查询用户信息是否正确
     */
    @Select("select * from user where account=#{arg0} and password=#{arg1}")
    User checkUser(String account, String password);


    @Insert("insert into user(phone_number,name,password,account) values(#{phoneNumber},#{name},#{password},#{account})")
    int insertUser(User user);

    /*
    注册时调用

    通过用户名、密码查询是否存在该用户
     */
    @Select("select * from user where phone_number=#{arg0} or account=#{arg1} limit 1")
    User checkForUser(String userPhone,String account);

    /**
    * @Description: 测试分页
    * @Param:
    * @return:
    * @Author: Anthony
    * @Date: 2022/1/26
    */
    @Select("select * from user")
    List<User> getUsers();

    @Select("select * from user where account=#{arg0}")
    User getForUser(String account);
}


