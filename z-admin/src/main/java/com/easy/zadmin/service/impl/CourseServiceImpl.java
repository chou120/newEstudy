package com.easy.zadmin.service.impl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.easy.zadmin.mapper.CourseMapper;
import com.easy.zadmin.pojo.dto.PageUser;
import com.easy.zadmin.pojo.entity.Course;
import com.easy.zadmin.pojo.entity.User;
import com.easy.zadmin.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author sanye
 * @Date 2023/9/2 13:08
 * @Version 1.0
 */
@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseMapper courseMapper;

    @Override
    public Integer createCourse(Course course) {
        course.setCourseStatus(0);
        course.setDelFlag(0);
        //course.setCreateTime(new Date());

        return courseMapper.createCourse(course);
    }

    @Override
    public List<Course> getCourseInfos(String   course) {
        return courseMapper.getCourseInfos(course);
    }

    @Override
    public Integer updateCourseStatus(Course course) {

        return courseMapper.updateCourseStatus(course);
    }

    @Override
    public Page<Course> selectPageVo(PageUser<Course> pageUser) {
        Page<Course> page = new Page<>(pageUser.getPageNo(), pageUser.getPageSize());
        Page<Course> coursePage = courseMapper.selectPageVo(page,pageUser.getT());

        return coursePage;
    }
}
