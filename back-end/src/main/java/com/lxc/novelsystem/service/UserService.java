package com.lxc.novelsystem.service;






import com.lxc.novelsystem.entity.ResponseResult;
import com.lxc.novelsystem.entity.User;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
    * @Description: 用户登录
    * @Param: [java.lang.String, java.lang.String]
    * @return: java.util.Map<java.lang.String,java.lang.String>
    * @Author: Anthony
    * @Date: 2022/2/28
    */
     Map<String, String> UserLogin( String account, String password);

     /**
     * @Description: 用户注册
     * @Param: [com.lxc.novelsystem.entity.User]
     * @return: java.util.Map<java.lang.String,java.lang.String>
     * @Author: Anthony
     * @Date: 2022/2/28
     */
    Map<String, String> RegisterUser( User user);
    /**
    * @Description: 测试分页
    * @Param:
    * @return:
    * @Author: Anthony
    * @Date: 2022/1/26
    */
    List<User> getUsers();
    /** 
    * @Description: 用户退出
    * @Param: []
    * @return: com.lxc.novelsystem.entity.ResponseResult
    * @Author: Anthony
    * @Date: 2022/2/28
    */
    public ResponseResult logout() ;
}
