package com.easy.zadmin.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.easy.zadmin.common.res.ResponseResult;
import com.easy.zadmin.pojo.dto.PageUser;
import com.easy.zadmin.pojo.entity.User;
import com.easy.zadmin.service.UserService;
import com.easy.zadmin.util.CheckEmail;
import com.easy.zadmin.util.CheckPassword;
import com.easy.zadmin.util.JwtUtil;
import com.easy.zadmin.util.PhoneFormatCheckUtils;
import io.jsonwebtoken.Claims;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @Author sanye
 * @Date 2023/8/24 23:58
 * @Version 1.0
 */
@RestController
public class UserController {
    private Logger LOGGER = LoggerFactory.getLogger(UserController.class);
    @Autowired
    private UserService userService;

    @GetMapping("/user/infos")
    public ResponseResult queryInfos(@RequestParam(value="teaName", defaultValue="") String  teaName) {
        LOGGER.info("进入用户查询列表");
        return userService.getUserInfos(teaName);
    }

    @PostMapping("/user/selectPageVo")
    public ResponseResult selectPageVo(@RequestBody PageUser<User> pageUser) {
        LOGGER.info("进入分页列表");
//        if(StringUtils.isBlank(pageUser.getUser().getUserName())){
//            return new ResponseResult("410","用户名");
//        }
        return new ResponseResult("200", userService.pageUser(pageUser));
    }


    @PostMapping("/user/createUser")
    public ResponseResult createUser(@RequestBody User user, HttpServletRequest request) {

        LOGGER.info("进入创建用户");

        if(StringUtils.isBlank(user.getUserName())){
            return ResponseResult.USERNAME_NOT_NULL;
        }
        if (StringUtils.isBlank(user.getPhonenumber())) {
            return ResponseResult.USER_MOBILE_NOT_EMPTY;
        }
        boolean mobileCheck= PhoneFormatCheckUtils.isPhoneLegal(user.getPhonenumber());
        if(!mobileCheck){
            return ResponseResult.USER_MOBILE_IS_TRUE;
        }
        if (StringUtils.isBlank(user.getPassword())) {
            return ResponseResult.USER_PASSWORD_NOT_EMPTY;
        }
        if(StringUtils.isBlank(user.getEmail())){
            return ResponseResult.USER_EMAIL_NOT_NULL;
        }
//        if(CheckEmail.isValidEmail(user.getEmail())){
//            return ResponseResult.EMAIL_FORMAT_NOT_TRUE;
//        }
        /**
         * 创建用户时用户密码是默认在前端生成的
         */
        String token = request.getHeader("token");
        String userid;
        try {
            Claims claims = JwtUtil.parseJWT(token);
            userid = claims.getSubject();
            LOGGER.info("当前用户id为{}", userid);
            return userService.createUser(user, userid);
        } catch (Exception e) {
            e.printStackTrace();
            return  ResponseResult.USER_CREATE_FAILED;
        }


    }



    @PostMapping("/user/delById")
    public  ResponseResult delById(@RequestBody User user){

        if(user.getId()==null){
            return ResponseResult.USERID_NOTEXISTS;
        }

        return userService.delUserById(user);
    }

    //  /user/editUserInfo


    @PostMapping("/user/editUserInfo")
    public  ResponseResult editUserInfo(@RequestBody User user){

        if(StringUtils.isBlank(user.getUserName())){
            return ResponseResult.USERNAME_NOT_NULL;
        }
        if(StringUtils.isBlank(user.getPassword())){
            return ResponseResult.USER_PASSWORD_NOT_EMPTY;
        }
        if(CheckPassword.checkPass(user.getPassword())){
            return  ResponseResult.USER_PASSWORD_SATISFY;
        }
        if (StringUtils.isBlank(user.getPhonenumber())) {
            return ResponseResult.USER_MOBILE_NOT_EMPTY;
        }
        boolean mobileCheck= PhoneFormatCheckUtils.isPhoneLegal(user.getPhonenumber());
        if(!mobileCheck){
            return ResponseResult.USER_MOBILE_IS_TRUE;
        }
        if(StringUtils.isBlank(user.getEmail())){
            return ResponseResult.USER_EMAIL_NOT_NULL;
        }
//        if(!CheckEmail.isValidEmail(user.getEmail())){
//            return ResponseResult.EMAIL_FORMAT_NOT_TRUE;
//        }

        return userService.editUser(user);
    }


    @PostMapping("/user/updateStatus")
    public  ResponseResult updateStatus(@RequestBody User user){

        if(user.getId()==null){
            return ResponseResult.USERID_NOTEXISTS;
        }
        if(user.getStatus()==null){
            return ResponseResult.STATUS_NULL;
        }

        return userService.updateStatus(user);

    }

    /**
     * 忘记密码
     */
    @PostMapping("/user/forgetPwd")
    public ResponseResult forgetPass(@RequestBody  User user){
        String email=user.getEmail();
        if(StringUtils.isBlank(email)){
         return   ResponseResult.USER_EMAIL_NOT_NULL;
        }
        return  userService.getUserByEmail(email);
    }

}
