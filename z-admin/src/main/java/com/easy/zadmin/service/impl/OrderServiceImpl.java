package com.easy.zadmin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.zadmin.common.res.ResponseResult;
import com.easy.zadmin.mapper.CustStuMapper;
import com.easy.zadmin.mapper.OrderMapper;
import com.easy.zadmin.mapper.RebackDetailMapper;
import com.easy.zadmin.mapper.UserMapper;
import com.easy.zadmin.pojo.dto.OrderDto;
import com.easy.zadmin.pojo.dto.PageUser;
import com.easy.zadmin.pojo.entity.Course;
import com.easy.zadmin.pojo.entity.CustStu;
import com.easy.zadmin.pojo.entity.Order;
import com.easy.zadmin.pojo.entity.User;
import com.easy.zadmin.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * @Author sanye
 * @Date 2023/9/5 21:21
 * @Version 1.0
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private CustStuMapper custStuMapper;

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private RebackDetailMapper rebackDetailMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult createOrder(OrderDto orderDto) {

        CustStu custStu = custStuMapper.getByCondition(orderDto.getCustStu());
        //判断余额是否足够  订单价格和客户的余额对比
        if (orderDto.getOrder().getOrderTotal() > custStu.getSurplusAmount()) {
            return ResponseResult.PLEASE_TO_TOP_UP;  //当前余额不足
        }
        Order order = orderDto.getOrder();
        order.setOrderId(UUID.randomUUID().toString());
        order.setOrderStatus(1); //进行中
        order.setCustomerName(orderDto.getCustStu().getCustName());
        order.setTeachName(orderDto.getUser().getUserName());
        order.setDelFlag(0);
        order.setStuId(orderDto.getCustStu().getId());
        order.setUserId(orderDto.getUser().getId());

        Integer res = orderMapper.createOrder(order);
//        if(res>0){
//            custStu.setUsedAmount(custStu.getUsedAmount()+orderDto.getOrder().getOrderTotal());//已经使用的
//            Integer integer = custStuMapper.editCustStu(custStu);
//        }
        return res > 0 ? ResponseResult.REQ_SUCCESS : ResponseResult.REQ_FAIL;

    }

    @Override
    public Page<Order> pageUser(PageUser<Order> pageUser) {
        Page<Order> page = new Page<>(pageUser.getPageNo(), pageUser.getPageSize());
        Page<Order> orderPage = orderMapper.selectPageVo(page, pageUser.getT());
        return orderPage;
    }


    @Transactional(rollbackFor = Exception.class)
    @Override
    public int updateBatch(List<Order> list) {
        int orderRes = orderMapper.updateBatch(list);
        //TODO  批量更新订单状态的同时将客户的余额减去订单的总额  进行更新操作
        int custRes=0;
        if(orderRes!=0){
            custRes=  orderMapper.updateBatchByEmailToCustomer(list);
        }
        return  custRes;
    }


    @Override
    public ResponseResult editOrder(OrderDto orderDto) {
        CustStu byCondition = custStuMapper.getByCondition(orderDto.getCustStu());
        //判断余额是否足够  订单价格和客户的余额对比
        if (orderDto.getOrder().getOrderTotal() > byCondition.getSurplusAmount()) {
            return ResponseResult.PLEASE_TO_TOP_UP;  //当前余额不足
        }
        Order order = orderDto.getOrder();
        order.setStuId(orderDto.getCustStu().getId());
        order.setUserId(orderDto.getUser().getId());
        order.setTeachName(orderDto.getUser().getUserName());

        return orderMapper.editOrder(order) > 0 ? ResponseResult.REQ_SUCCESS : ResponseResult.REQ_FAIL;
    }


    /**
     * 查询订单详情
     * @param order
     * @return
     */
    @Override
    public ResponseResult queryOrder(Order order) {

        Order newOrder = orderMapper.queryOrder(order.getOrderId());
        CustStu custStu = custStuMapper.queryCustStu(newOrder.getStuId());
        User user = userMapper.queryUser(newOrder.getUserId());
        OrderDto orderDto = new OrderDto();
        orderDto.setOrder(newOrder);
        orderDto.setCustStu(custStu);
        orderDto.setUser(user);

        return new ResponseResult("200", orderDto);
    }

    /**
     * 退单功能
     * @param orderDto
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult rebackOrder(OrderDto orderDto) {

        orderDto.getRebackDetail().setOrderId(orderDto.getOrder().getOrderId());

        Integer rebackDetail = rebackDetailMapper.createRebackDetail(orderDto.getRebackDetail());
        if(rebackDetail>0){
            Order order = orderMapper.queryOrder(orderDto.getOrder().getOrderId());
            CustStu custStu = custStuMapper.queryCustStu(order.getStuId());
            //更新客户的余额=原纪录+退单金额
            custStu.setSurplusAmount(custStu.getSurplusAmount()+orderDto.getRebackDetail().getRebackMoney());
            //更新客户已经使用的money=原纪录-退单金额
            custStu.setUsedAmount(custStu.getUsedAmount()-orderDto.getRebackDetail().getRebackMoney());
            Integer res = custStuMapper.editCustStu(custStu);

            return res>0?ResponseResult.REQ_SUCCESS:ResponseResult.REQ_FAIL;

        }
        return ResponseResult.REQ_FAIL;
    }
}
