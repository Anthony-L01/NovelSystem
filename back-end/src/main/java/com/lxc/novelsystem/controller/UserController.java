package com.lxc.novelsystem.controller;

import com.alibaba.fastjson.JSONObject;

import com.lxc.novelsystem.entity.ResponseResult;
import com.lxc.novelsystem.entity.User;
import com.lxc.novelsystem.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Api(tags = "用户控制类")
@RestController()
public class UserController {

    @Autowired
    UserService userService;

   /** 
   * @Description: 用户登录
   * @Param: [com.alibaba.fastjson.JSONObject]
   * @return: java.util.Map<java.lang.String,java.lang.String>
   * @Author: Anthony
   * @Date: 2022/2/28
   */
    @ApiOperation("用户登录")
    @PostMapping("/NovelSystem/UserLogin")
    Map<String, String> UserLogin(@RequestBody@ApiParam("例子:\n"+
            "{\n" +

            "      \"account\":\"\",\n" +
            "      \"password\":\"\"\n" +
            " }\n") JSONObject jsonObject)
    {
      return   userService.UserLogin(jsonObject.getString("account"),jsonObject.getString("password"));
    }

 /** 
 * @Description: 用户注册
 * @Param: [com.alibaba.fastjson.JSONObject]
 * @return: java.util.Map<java.lang.String,java.lang.String>
 * @Author: Anthony
 * @Date: 2022/2/28
 */
    @ApiOperation("用户注册")
    @PostMapping("/NovelSystem/UserRegister")
    Map<String, String> UserRegister(@RequestBody@ApiParam("例子:\n"+
            "{\n"+
            "\"state\":\"register\", \n"+
            "\"name\":\"lxc\",\n"+
            "\"phone_number\":\" 123\",\n"+
            "\"account\":\"123\",\n"+
            "\"password\":\"123\"\n"+
            " }\n"
    ) JSONObject jsonObject)
    {
        User user=new User(jsonObject.getString("phone_number"),jsonObject.getString("name"),jsonObject.getString("password"),jsonObject.getString("account"));

        return   userService.RegisterUser(user);
    }
    /** 
    * @Description: 用户退出
    * @Param: []
    * @return: com.lxc.novelsystem.entity.ResponseResult
    * @Author: Anthony
    * @Date: 2022/2/28
    */

    @GetMapping("/NovelSystem/UserLogout")
    public ResponseResult UserLogout()
    {
        return userService.logout();
    }
}
