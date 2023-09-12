package com.easy.zadmin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.zadmin.common.res.ResponseResult;
import com.easy.zadmin.pojo.dto.PageUser;
import com.easy.zadmin.pojo.entity.ChargeRecord;
import com.easy.zadmin.pojo.entity.Course;

/**
 * @Author sanye
 * @Date 2023/9/2 20:08
 * @Version 1.0
 */

public interface ChargeRecordService {

    ResponseResult createChargeRecord(ChargeRecord chargeRecord);

    Page<ChargeRecord> selectPageVo(PageUser<ChargeRecord> pageUser);
}
