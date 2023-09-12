package com.easy.zadmin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.zadmin.common.res.ResponseResult;
import com.easy.zadmin.pojo.dto.OrderDto;
import com.easy.zadmin.pojo.dto.PageUser;
import com.easy.zadmin.pojo.entity.CustStu;
import com.easy.zadmin.pojo.entity.Order;

import java.util.List;

/**
 * @Author sanye
 * @Date 2023/9/5 21:19
 * @Version 1.0
 */
public interface OrderService {

    ResponseResult  createOrder(OrderDto order);

    Page<Order> pageUser(PageUser<Order> pageUser);

    int  updateBatch(List<Order> list);

    ResponseResult editOrder(OrderDto  orderDto);

    //查询订单详情
    ResponseResult queryOrder(Order  order);

    ResponseResult rebackOrder(OrderDto orderDto);


}
