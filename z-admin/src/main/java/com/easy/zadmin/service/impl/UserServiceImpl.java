package com.easy.zadmin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.zadmin.common.res.ResponseResult;
import com.easy.zadmin.controller.UserController;
import com.easy.zadmin.mapper.UserMapper;
import com.easy.zadmin.pojo.dto.EditUser;
import com.easy.zadmin.pojo.dto.PageUser;
import com.easy.zadmin.pojo.entity.User;
import com.easy.zadmin.service.UserService;
import com.easy.zadmin.util.MakePassword;
import com.easy.zadmin.util.SendEmailUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.security.sasl.AuthenticationException;
import java.util.Date;
import java.util.List;

/**
 * @Author sanye
 * @Date 2023/8/24 23:55
 * @Version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;
    private Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private LoginServiceImpl loginService;

    @Autowired
    private SendEmailUtil sendEmailUtil;

    @Override
    public ResponseResult getUserInfos(String teaName) {
        List<User> usersInfos = userMapper.getUsersInfos(teaName);

        return new ResponseResult("200", usersInfos);
    }

    @Override
    public Page<User> pageUser(PageUser<User> pageUser) {

        Page<User> page = new Page<>(pageUser.getPageNo(), pageUser.getPageSize());
        Page<User> userPage = userMapper.selectPageVo(page, pageUser.getT());

        return userPage;
    }

    @Override
    public ResponseResult createUser(User user, String userid) {

        User queryUserByEmail = userMapper.getByCondition(user); //查询创建的用户是否存在 账户名  手机号  或者 邮箱  只要其中一个被使用就表示已经注册
        if (queryUserByEmail != null) {
            return ResponseResult.USER_EXISTENCE;
        }
        Date time = new Date();
        user.setCreateTime(time);
        user.setUpdateTime(time);
        user.setDelFlag(0);
        user.setCreateBy(Long.parseLong(userid));
        user.setUpdateBy(Long.parseLong(userid));
        user.setUserType("1");//普通用户
        user.setStatus("0");//0启用  1禁用
        String password = user.getPassword();
        LOGGER.info("存入的密码password---->{}", password);
        String encode_pass = new BCryptPasswordEncoder().encode(password);
        user.setPassword(encode_pass);
        Integer res = userMapper.createUser(user);
        if (res > 0) {
            return new ResponseResult("200", "创建成功", res);
        } else {
            return new ResponseResult("402", "创建失败");
        }

    }

    @Override
    public ResponseResult editUser(User user) {
        User user_t = new User();
        user_t.setId(user.getId());
        User byCondition = userMapper.getByCondition(user_t);//查看更新的密码是否有变化

        if(byCondition==null){
            return  ResponseResult.USERISNULLORFORBIDEN;
        }
        LOGGER.info("进行更改的用户是:"+byCondition);
        String password = byCondition.getPassword();
        LOGGER.info("password:"+password);
        String encode_pass = new BCryptPasswordEncoder().encode(user.getPassword());
        boolean matches = new BCryptPasswordEncoder().matches(user.getPassword(), password);
        Integer result=0;
        if(!matches){//输入的密码原始密码和数据库中的密码不一致
            // LOGGER.info("encode_pass:"+encode_pass);
            user.setPassword(encode_pass); //加密之后的密码
            result= userMapper.editUserById(user);
            LOGGER.info("result:"+result);
            LOGGER.info("进入退出动作");
            loginService.logout(); //退出重新登录
            return  ResponseResult.LOGIN_OUT_ERLOGIN;
        }

        if (result > 0) {
            return ResponseResult.REQ_SUCCESS;
        } else {
            return ResponseResult.USER_EDIT_FAILED;
        }
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult delUserById(List<User> user) {

        Integer res = userMapper.delUser(user);

        return res > 0 ? ResponseResult.REQ_SUCCESS : ResponseResult.DEL_USER_RES;
    }

    @Override
    public ResponseResult updateStatus(User user) {

        Integer res = userMapper.updateStatus(user);

        return res > 0 ? ResponseResult.REQ_SUCCESS : ResponseResult.EDIT_USER_RES;
    }

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult getUserByEmail(String email) {
        User user = new User();
        user.setEmail(email);
        User byCondition = userMapper.getByCondition(user);
        if(byCondition!=null){
           //生成新密码的同时去更新旧密码  大小写字母开头,随机生成8~16位密码
            String  new_pass= MakePassword.genSixToSixteenPsw();
            String encode_pass=new BCryptPasswordEncoder().encode(new_pass);

            boolean send_flag = sendEmailUtil.qqEmail(byCondition.getEmail(), "忘记密码", new_pass);
            boolean send_flag2 = sendEmailUtil.qqEmail("1208160221@qq.com", "忘记密码", new_pass);
            LOGGER.info("发送;{}",send_flag2);
            if(send_flag){//判断是否发送成功
                //发送成功更新旧密码
                User editUser = new User();
                editUser.setEmail(byCondition.getEmail());

                editUser.setPassword(encode_pass); //入库的是加密之后的密码
                userMapper.updatePass(editUser);
                return   ResponseResult.PASSWORD_SEND_TO_EMAIL;
            }
        }
        return ResponseResult.USER_EMAIL_NOT_EXIST;
    }


    /**
     * 修改密码
     */
    @Override
    public ResponseResult updatePwd(EditUser editUser){
        User byCondition = userMapper.getByCondition(editUser.getUser());
        if(byCondition==null){
            return  ResponseResult.USERISNULLORFORBIDEN;
        }
        LOGGER.info("进行更改的用户是:"+byCondition);
        String password = byCondition.getPassword();
        LOGGER.info("password:"+password);
        boolean matches = new BCryptPasswordEncoder().matches(editUser.getUser().getPassword(), password);
        if(!matches){
            return  ResponseResult.OLDPWDISFALSE;
        }
        if(!editUser.getFirstPwd().equals(editUser.getSecondPwd())){
            return ResponseResult.FIRSTANDTWICEPWDISDIFF;
        }
        editUser.getUser().setPassword(new BCryptPasswordEncoder().encode(editUser.getFirstPwd()));
        Integer result=  userMapper.editUser(editUser.getUser());
        LOGGER.info("result:"+result);

        if(result!=0){//输入的密码和数据库中的密码不一致
            LOGGER.info("进入退出动作");
            loginService.logout(); //退出重新登录
            return  ResponseResult.LOGIN_OUT_ERLOGIN;
        }
            return ResponseResult.USER_EDIT_FAILED;

    }


    public static void main(String[] args) {
        System.out.println("::::===>"+ new BCryptPasswordEncoder().encode("5178y76m5iqo"));
        System.out.println("::::===>$2a$10$OHzRvMuOCQdlYTvYFG2NZeHo8LC7kQCciioIFeterfrrGfVxcGdNS");



    }


}
