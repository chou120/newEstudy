package com.easy.zadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.zadmin.pojo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserMapper extends BaseMapper<User> {


    /**
     * 增删改
     */
    Integer createUser(@Param("user") User user);

    Integer editUser(@Param("user") User user);

    Integer delUser(@Param("user") User user);

    List<User> getUsersInfos(@Param("teaName") String teaName);

    User  getByCondition(@Param("user")User user);

    Integer updateStatus(@Param("user") User user);

    Integer  updatePass(@Param("user") User user);

    /**
     * 如果想通过分页插件作用于我们自己定义的函数,第一个参数必须是Page对象
     * 通过年龄查询用户信息并分页
     *
     * @param page MyBatis-Plus所提供的分页对象，必须位于第一个参数的位置
     * @param user
     * @return
     */
    Page<User> selectPageVo(@Param("page") Page<User> page, @Param("user") User user);

    User  queryUser(@Param("userId")Long  userId);


}
