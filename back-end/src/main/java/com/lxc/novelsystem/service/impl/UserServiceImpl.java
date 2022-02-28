package com.lxc.novelsystem.service.impl;


import com.lxc.novelsystem.Util.JwtUtil;
import com.lxc.novelsystem.Util.RedisCache;
import com.lxc.novelsystem.entity.LoginUser;
import com.lxc.novelsystem.entity.ResponseResult;
import com.lxc.novelsystem.entity.User;
import com.lxc.novelsystem.mapper.UserMapper;
import com.lxc.novelsystem.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Slf4j
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    UserMapper userMapper;

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

/*
用户登录
 */
    @Override
    public Map<String, String> UserLogin(String account, String password) {

        User user;
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(account, password);
        Authentication authenticate = authenticationManager.authenticate(authenticationToken);
        if (Objects.isNull(authenticate)) {
            log.error("用户名或密码错误");
            throw new RuntimeException("用户名或密码错误");
        }
        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String Account = loginUser.getUser().getAccount().toString();
        log.info("获取用户账号",Account);
        String jwt = JwtUtil.createJWT(Account);
        log.info("生成JWT");
        redisCache.setCacheObject("login:" + Account, loginUser);
        Map<String, String> map = new HashMap<>();

        map.put("token", jwt);
//                map.put("login_check", "true");
        map.put("name", loginUser.getUser().getName().toString());
        map.put("account",account);
           return map;

    }

    @Override
    public Map<String, String> RegisterUser(User user) {
        User user1;
        Map<String,String> map = new HashMap<>();
        if((user1=userMapper.checkForUser(user.getPhoneNumber(),user.getAccount()))==null)
        {
            String mima=user.getPassword();
            user.setPassword(passwordEncoder.encode(mima));
            userMapper.insertUser(user);
            map.put("register_check","true");
            map.put("name",user.getName());
            map.put("account",user.getAccount());
            log.info("注册成功");
        }
        else
        {
            map.put("register_check","false");
            map.put("name","no_name");
            map.put("account","no_account");
            log.info("注册失败");
        }
      return map;
    }

    @Override
    public List<User> getUsers() {
        return userMapper.getUsers();
    }

    @Override
    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        String account = loginUser.getUser().getAccount();
        //删除用户信息
        redisCache.deleteObject("login:"+account);
        return new ResponseResult(200,"退出成功");
    }
}
