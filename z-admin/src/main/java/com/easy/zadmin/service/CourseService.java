package com.easy.zadmin.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.zadmin.common.res.ResponseResult;
import com.easy.zadmin.pojo.dto.PageUser;
import com.easy.zadmin.pojo.entity.Course;
import com.easy.zadmin.pojo.entity.User;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Author sanye
 * @Date 2023/9/2 13:07
 * @Version 1.0
 */
@Repository
public interface CourseService {
    Integer createCourse( Course course);

    List<Course> getCourseInfos(String  course);

    Integer updateCourseStatus(Course course);

    Page<Course> selectPageVo(PageUser<Course> pageUser);


}
