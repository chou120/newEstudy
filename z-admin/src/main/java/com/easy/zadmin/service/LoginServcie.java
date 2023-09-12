package com.easy.zadmin.service;

import com.easy.zadmin.common.res.ResponseResult;
import com.easy.zadmin.pojo.entity.User;
import org.springframework.http.ResponseEntity;

public interface LoginServcie {
    ResponseResult login(User user);
    ResponseResult logout();


}
