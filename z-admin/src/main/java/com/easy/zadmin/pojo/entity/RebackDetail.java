package com.easy.zadmin.pojo.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author sanye
 * @Date 2023/9/6 21:49
 * @Version 1.0
 */

//reback_detail
@TableName(value="reback_detail")
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RebackDetail {

    private Long rebackId;
    private String orderId;
    private  String remark;
    private Double rebackMoney;
    private Integer rebackType;

    public Integer getRebackType() {
        return rebackType;
    }

    public void setRebackType(Integer rebackType) {
        this.rebackType = rebackType;
    }

    public Long getRebackId() {
        return rebackId;
    }

    public void setRebackId(Long rebackId) {
        this.rebackId = rebackId;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Double getRebackMoney() {
        return rebackMoney;
    }

    public void setRebackMoney(Double rebackMoney) {
        this.rebackMoney = rebackMoney;
    }
}
