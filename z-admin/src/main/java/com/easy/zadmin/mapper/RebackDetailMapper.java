package com.easy.zadmin.mapper;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.zadmin.pojo.entity.Order;
import com.easy.zadmin.pojo.entity.RebackDetail;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author sanye
 * @Date 2023/9/5 20:58
 * @Version 1.0
 */
@Repository
public interface RebackDetailMapper {

    Integer createRebackDetail(@Param("rebackDetail") RebackDetail rebackDetail);

    RebackDetail queryDetail(@Param("orderId") String  orderId);

}
