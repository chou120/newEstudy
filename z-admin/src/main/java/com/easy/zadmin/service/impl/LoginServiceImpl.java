package com.easy.zadmin.service.impl;

import com.easy.zadmin.common.res.ResponseResult;
import com.easy.zadmin.controller.LoginController;
import com.easy.zadmin.pojo.dto.LoginUser;
import com.easy.zadmin.pojo.entity.User;
import com.easy.zadmin.service.LoginServcie;
import com.easy.zadmin.util.JwtUtil;
import com.easy.zadmin.util.RedisCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Objects;

@Service
@SuppressWarnings({"unchecked"})
public class LoginServiceImpl implements LoginServcie {

    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private RedisCache redisCache;

    private Logger LOGGER = LoggerFactory.getLogger(LoginServiceImpl.class);

    @Override
    public ResponseResult login(User user) {
        UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getEmail(),user.getPassword());
        LOGGER.error("authenticationToken.getPrincipal():"+authenticationToken.getPrincipal());
        LOGGER.error("authenticationToken.getCredentials():"+authenticationToken.getCredentials());
        Authentication authenticate=null;
        try{
            //如果输入的账户和密码错误，通常情况下会抛出AuthenticationException异常，而不是直接返回数据。这是一种安全机制，用于防止未经授权的访问
            authenticate  = authenticationManager.authenticate(authenticationToken);
        }catch (Exception e){
            LOGGER.info("e:"+e);
        }

        LOGGER.info("authenticate:"+authenticate);

        if(Objects.isNull(authenticate)){
         return    ResponseResult.USER_OR_PASSWORD_ERROR;
        }
        //使用userid生成token
        LoginUser loginUser = (LoginUser) authenticate.getPrincipal();
        String userId = loginUser.getUser().getId().toString(); //9001
        String jwt = JwtUtil.createJWT(userId);
        //authenticate存入redis
        redisCache.setCacheObject("login:"+userId,loginUser);
        //把token响应给前端
        HashMap<String,Object> map = new HashMap<>();
        map.put("token",jwt);
        map.put("email",loginUser.getUser().getEmail());
        map.put("permissions",loginUser.getPermissions());

        return new ResponseResult("200","登陆成功",map);
    }
    @Override
    public ResponseResult logout() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUser loginUser = (LoginUser) authentication.getPrincipal();
        Long userid = loginUser.getUser().getId();
        redisCache.deleteObject("login:"+userid);
        return ResponseResult.LOGIN_OUT;
    }
}

