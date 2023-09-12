package com.easy.zadmin.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.zadmin.pojo.entity.Course;
import com.easy.zadmin.pojo.entity.CustStu;
import com.easy.zadmin.pojo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author sanye
 * @Date 2023/9/2 10:52
 * @Version 1.0
 */
@Repository
public interface CourseMapper  extends BaseMapper<Course> {
    /**
     * 增删改
     */
    Integer createCourse(@Param("course") Course course);

//    Integer editCourse(@Param("course") Course course);
//
//    Integer delCourse(@Param("course") Course course);

    List<Course> getCourseInfos(@Param("course") String course);

   // User  getByCondition(@Param("course")Course course);

    Integer updateCourseStatus(@Param("course") Course course);

    //Integer  updatePass(@Param("course") Course course);

    /**
     * 如果想通过分页插件作用于我们自己定义的函数,第一个参数必须是Page对象
     * 通过年龄查询用户信息并分页
     *
     * @param page MyBatis-Plus所提供的分页对象，必须位于第一个参数的位置
     * @param course
     * @return
     */
    Page<Course> selectPageVo(@Param("page") Page<Course> page, @Param("course") Course course);
}
