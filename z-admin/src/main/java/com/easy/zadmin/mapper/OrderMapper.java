package com.easy.zadmin.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.zadmin.pojo.entity.Order;
import com.easy.zadmin.pojo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author sanye
 * @Date 2023/9/5 20:58
 * @Version 1.0
 */
@Repository
public interface OrderMapper {

    Integer createOrder(@Param("order") Order order);
    Integer delOrder(@Param("order") Order order);
    Integer editOrder(@Param("order") Order order);
    Order queryOrder(@Param("orderId") String orderId);
    Page<Order> selectPageVo(@Param("page") Page<Order> page, @Param("order") Order order);

    int updateBatch(@Param("list") List<Order> list);
    int  updateBatchByEmailToCustomer(@Param("list") List<Order> list);

}
