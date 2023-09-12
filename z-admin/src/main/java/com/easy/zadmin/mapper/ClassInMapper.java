package com.easy.zadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.zadmin.pojo.entity.ChargeRecord;
import com.easy.zadmin.pojo.entity.ClassInVo;
import com.easy.zadmin.pojo.entity.Order;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author sanye
 * @Date 2023/9/2 21:43
 * @Version 1.0
 */
@Repository
public interface ClassInMapper extends BaseMapper<ClassInVo> {
    //分页
    Page<ClassInVo> selectPageVo(@Param("page") Page<Order> page, @Param("order") Order order);
    //创建记录
    Integer  createClassInVo(@Param("classInVo") ClassInVo classInVo);

}
