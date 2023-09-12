package com.easy.zadmin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.zadmin.common.res.ResponseResult;
import com.easy.zadmin.mapper.ChargeMapper;
import com.easy.zadmin.pojo.dto.PageUser;
import com.easy.zadmin.pojo.entity.ChargeRecord;
import com.easy.zadmin.pojo.entity.Course;
import com.easy.zadmin.service.ChargeRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @Author sanye
 * @Date 2023/9/2 20:10
 * @Version 1.0
 */
@Service
public class ChargeRecordServiceImpl  implements ChargeRecordService {

    @Autowired
    private ChargeMapper chargeMapper;

    @Override
    public ResponseResult createChargeRecord(ChargeRecord chargeRecord) {

        Integer res = chargeMapper.createChargeRecord(chargeRecord);

        return  res>0?ResponseResult.REQ_SUCCESS:ResponseResult.REQ_FAIL;
    }

    @Override
    public Page<ChargeRecord> selectPageVo(PageUser<ChargeRecord> pageUser) {
        Page<ChargeRecord> page = new Page<>(pageUser.getPageNo(), pageUser.getPageSize());
        Page<ChargeRecord> chargeRecordPage = chargeMapper.selectPageVo(page,pageUser.getT());
        return chargeRecordPage;
    }
}
