package com.easy.zadmin.controller;


import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.easy.zadmin.common.res.ResponseResult;
import com.easy.zadmin.pojo.entity.User;
import com.easy.zadmin.service.LoginServcie;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {
    /**
     * 让SpringSecurity对这个接口放行,让用户访问这个接口的时候不用登录也能访问。
     * 在接口中我们通过AuthenticationManager的authenticate方法来进行用户认证,
     * 所以需要在SecurityConfig中配置把AuthenticationManager注入容器。
     * 认证成功的话要生成一个jwt，放入响应中返回。并且为了让用户下回请求时能通过jwt识别出具体的是哪个用户，
     * 我们需要把用户信息存入redis，可以把用户id作为key。
     */

    private Logger LOGGER = LoggerFactory.getLogger(LoginController.class);


    @Autowired
    private LoginServcie loginServcie;

    @PostMapping("/user/exUserLogin") //sys:user:list
   //@PreAuthorize("hasAuthority('sys:user:list')")
    public ResponseResult login(@RequestBody User user) {

        if(StringUtils.isBlank(user.getEmail())){
            return ResponseResult.USER_EMAIL_NOT_NULL;
        }
        if(StringUtils.isBlank(user.getPassword())){
            return ResponseResult.USER_PASSWORD_NOT_EMPTY;
        }

        return loginServcie.login(user);
    }
    @RequestMapping("/hello")
    //@PreAuthorize("hasAuthority('test')")
    public String hello(){
        return "hello";
    }

}
