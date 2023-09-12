package com.easy.zadmin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.zadmin.common.res.ResponseResult;
import com.easy.zadmin.mapper.ChargeMapper;
import com.easy.zadmin.mapper.ClassInMapper;
import com.easy.zadmin.mapper.OrderMapper;
import com.easy.zadmin.pojo.dto.PageUser;
import com.easy.zadmin.pojo.entity.ChargeRecord;
import com.easy.zadmin.pojo.entity.ClassInVo;
import com.easy.zadmin.pojo.entity.Order;
import com.easy.zadmin.service.ChargeRecordService;
import com.easy.zadmin.service.ClassInService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author sanye
 * @Date 2023/9/2 20:10
 * @Version 1.0
 */
@Service
public class ClassInServiceImpl implements ClassInService {

    @Autowired
    private ClassInMapper classInMapper;

    @Autowired
    private OrderMapper orderMapper;

    @Override
    public ResponseResult createClassInVo(ClassInVo ClassInVo) {

        Integer res = classInMapper.createClassInVo(ClassInVo);

        return  res>0?ResponseResult.REQ_SUCCESS:ResponseResult.REQ_FAIL;
    }

    @Override
    public Page<Order> selectPageVo(PageUser<Order> pageUser) {
        Page<Order> page = new Page<>(pageUser.getPageNo(), pageUser.getPageSize());
        Page<Order> orderPage = orderMapper.selectPageVo(page,pageUser.getT());
        return orderPage;
    }
}
