package com.easy.zadmin.pojo.dto;

import com.easy.zadmin.pojo.entity.CustStu;
import com.easy.zadmin.pojo.entity.Order;
import com.easy.zadmin.pojo.entity.RebackDetail;
import com.easy.zadmin.pojo.entity.User;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author sanye
 * @Date 2023/9/5 21:27
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class OrderDto {
    private CustStu custStu;//客户
    private User user;//老师
    private Order order;
    private RebackDetail rebackDetail;

    public RebackDetail getRebackDetail() {
        return rebackDetail;
    }

    public void setRebackDetail(RebackDetail rebackDetail) {
        this.rebackDetail = rebackDetail;
    }

    public CustStu getCustStu() {
        return custStu;
    }

    public void setCustStu(CustStu custStu) {
        this.custStu = custStu;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
