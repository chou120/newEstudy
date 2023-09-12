package com.easy.zadmin.pojo.dto;

import com.easy.zadmin.pojo.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author sanye
 * @Date 2023/8/25 20:32
 * @Version 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageUser<T> {

    private T  t;
    private Integer pageSize;
    private Integer pageNo;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }

    public Integer getPageNo() {
        return pageNo;
    }

    public void setPageNo(Integer pageNo) {
        this.pageNo = pageNo;
    }
}
