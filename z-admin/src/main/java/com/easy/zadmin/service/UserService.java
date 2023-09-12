package com.easy.zadmin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.zadmin.common.res.ResponseResult;
import com.easy.zadmin.pojo.dto.PageUser;
import com.easy.zadmin.pojo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author sanye
 * @Date 2023/8/24 23:54
 * @Version 1.0
 */
public interface UserService {

    ResponseResult  getUserInfos(String  teaName);
    Page<User>  pageUser(PageUser<User> pageUser);
    ResponseResult createUser(User user,String userid);
    ResponseResult editUser( User user);
    ResponseResult delUserById(User user);

    ResponseResult  updateStatus(User user);

    ResponseResult  getUserByEmail(String  email);


}
