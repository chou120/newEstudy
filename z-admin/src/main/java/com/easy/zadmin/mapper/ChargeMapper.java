package com.easy.zadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.zadmin.pojo.dto.PageUser;
import com.easy.zadmin.pojo.entity.ChargeRecord;
import com.easy.zadmin.pojo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * @Author sanye
 * @Date 2023/9/2 19:55
 * @Version 1.0
 */
@Repository
public interface ChargeMapper  extends BaseMapper<ChargeRecord> {

    //分页
    Page<ChargeRecord> selectPageVo(@Param("page") Page<ChargeRecord> page, @Param("chargeRecord") ChargeRecord chargeRecord);

    //创建记录
    Integer  createChargeRecord(@Param("chargeRecord") ChargeRecord chargeRecord);
}
