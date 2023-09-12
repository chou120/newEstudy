package com.easy.zadmin.pojo.dto;

import com.baomidou.mybatisplus.annotation.TableName;
import com.easy.zadmin.pojo.entity.CustStu;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author sanye
 * @Date 2023/9/2 10:31
 * @Version 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class RefoudCustStu {
    private CustStu  custStu;
    private Integer  type;

    public CustStu getCustStu() {
        return custStu;
    }

    public void setCustStu(CustStu custStu) {
        this.custStu = custStu;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
