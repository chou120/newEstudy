package com.easy.zadmin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.zadmin.common.res.ResponseResult;
import com.easy.zadmin.pojo.dto.PageUser;
import com.easy.zadmin.pojo.dto.RefoudCustStu;
import com.easy.zadmin.pojo.entity.CustStu;
import com.easy.zadmin.pojo.entity.User;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * @Author sanye
 * @Date 2023/8/29 20:12
 * @Version 1.0
 */
public interface CustStuService {

    ResponseResult createCustStu(CustStu custStu);

    ResponseResult editCustStu(CustStu custStu);

    ResponseResult delCustStu(CustStu custStu);

    List<User> getCustStuInfos(String  username);

    ResponseResult renewalOfPayment(RefoudCustStu refoudCustStu);

   // ResponseResult  refund( CustStu custStu);

    Page<CustStu> pageUser(PageUser<CustStu> pageUser);

}
