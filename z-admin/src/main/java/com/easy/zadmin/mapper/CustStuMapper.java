package com.easy.zadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.zadmin.pojo.entity.CustStu;
import com.easy.zadmin.pojo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CustStuMapper extends BaseMapper<CustStu> {


    /**
     * 增删改
     */
    Integer createCustStu(@Param("custStu") CustStu custStu);

    Integer editCustStu(@Param("custStu") CustStu custStu);

    Integer delCustStu(@Param("custStu") CustStu custStu);

    List<User> getCustStuInfos(@Param("username") String username);

    CustStu  getByCondition(@Param("custStu")CustStu custStu);

    Integer renewalOfPayment(@Param("custStu") CustStu custStu);

    Integer  refund(@Param("custStu") CustStu custStu);

    /**
     * 如果想通过分页插件作用于我们自己定义的函数,第一个参数必须是Page对象
     * 通过年龄查询用户信息并分页
     *
     * @param page MyBatis-Plus所提供的分页对象，必须位于第一个参数的位置
     * @param custStu
     * @return
     */
    Page<CustStu> selectPageVo(@Param("page") Page<CustStu> page, @Param("custStu") CustStu custStu);

    CustStu    queryCustStu(@Param("stuId")Long  stuId);


}
