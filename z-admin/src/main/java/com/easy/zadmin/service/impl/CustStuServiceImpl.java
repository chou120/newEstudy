package com.easy.zadmin.service.impl;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.zadmin.common.res.ResponseResult;
import com.easy.zadmin.mapper.CustStuMapper;
import com.easy.zadmin.pojo.dto.PageUser;
import com.easy.zadmin.pojo.dto.RefoudCustStu;
import com.easy.zadmin.pojo.entity.ChargeRecord;
import com.easy.zadmin.pojo.entity.CustStu;
import com.easy.zadmin.pojo.entity.User;
import com.easy.zadmin.service.ChargeRecordService;
import com.easy.zadmin.service.CustStuService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * @Author sanye
 * @Date 2023/8/29 20:12
 * @Version 1.0
 */
@Service
public class CustStuServiceImpl implements CustStuService {

    private Logger LOGGER = LoggerFactory.getLogger(CustStuServiceImpl.class);
    @Autowired
    private CustStuMapper custStuMapper;

    @Autowired
    private ChargeRecordService chargeRecordService;

    @Override
    public ResponseResult createCustStu(CustStu custStu) {
        Double total = custStu.getTotal();

        CustStu byCondition_custStu = custStuMapper.getByCondition(custStu);

        if(byCondition_custStu==null){
            custStu.setCustStatus(0);
            custStu.setDelFlag(0);
            custStu.setSurplusAmount(total);

            Integer result = custStuMapper.createCustStu(custStu);

            return result>0?ResponseResult.REQ_SUCCESS:ResponseResult.CRETAECUST_FAIL; //请求成功
        }
        return ResponseResult.CUSTNAME_REPETITION;
    }

    @Override
    public ResponseResult editCustStu(CustStu custStu) {

        return custStuMapper.editCustStu(custStu)>0?ResponseResult.REQ_SUCCESS:ResponseResult.REQ_FAIL;
    }

    @Override
    public ResponseResult delCustStu(CustStu custStu) {
        return custStuMapper.delCustStu(custStu)>0?ResponseResult.REQ_SUCCESS:ResponseResult.REQ_FAIL;
    }

    @Override
    public List<User> getCustStuInfos(String username) {
        List<User> custStuInfos = custStuMapper.getCustStuInfos(username);

        return custStuInfos;
    }
    @Transactional(rollbackFor = Exception.class)
    @Override
    public ResponseResult renewalOfPayment(RefoudCustStu refoudCustStu) {
        /**
         * 续费
         * 先查询出续费的客户 根据客户id
         */
        CustStu custStu = refoudCustStu.getCustStu();
        CustStu byCondition_custStu = custStuMapper.getByCondition(custStu);
        Double total =0.0;
        Integer renewalOfPayment=0;
        if(refoudCustStu.getType()==1){//续费
            total=  custStu.getTotal()+byCondition_custStu.getTotal();

            custStu.setTotal(total);
            custStu.setSurplusAmount(byCondition_custStu.getSurplusAmount()==null?0:byCondition_custStu.getSurplusAmount()+custStu.getTotal());
            renewalOfPayment = custStuMapper.renewalOfPayment(custStu);
        }else if(refoudCustStu.getType()==2){ //退款
            Double db_total = byCondition_custStu.getTotal();
            Double db_surplusAmount= byCondition_custStu.getSurplusAmount();
            //余额小于退款的钱
            if(db_surplusAmount < custStu.getTotal()){
                return ResponseResult.PLEASE_TO_TOP_UP;
            }
            total=db_total -custStu.getTotal();//退款的总金额需要变动
            Double surplusAmount=byCondition_custStu.getSurplusAmount()-custStu.getTotal();
            custStu.setTotal(total);
            custStu.setSurplusAmount(surplusAmount); //退款之后的余额
            renewalOfPayment=custStuMapper.renewalOfPayment(custStu);
        }else {
            return ResponseResult.REQ_FAIL;
        }
        ChargeRecord chargeRecord = new ChargeRecord();
        chargeRecord.setChargeType(refoudCustStu.getType());
        chargeRecord.setCustomerName(byCondition_custStu.getCustName());
        chargeRecord.setMoney(custStu.getTotal());

        ResponseResult result = chargeRecordService.createChargeRecord(chargeRecord);

        LOGGER.info("充值记录或者退款记录为："+result);

        return  renewalOfPayment>0?ResponseResult.REQ_SUCCESS:ResponseResult.REQ_FAIL;
    }


    @Override
    public Page<CustStu> pageUser(PageUser<CustStu> pageUser) {

        Page<CustStu> page = new Page<>(pageUser.getPageNo(), pageUser.getPageSize());
        Page<CustStu> custStuPage = custStuMapper.selectPageVo(page, pageUser.getT());

        return custStuPage;
    }


}
