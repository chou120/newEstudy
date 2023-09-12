package com.easy.zadmin.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.easy.zadmin.common.res.ResponseResult;
import com.easy.zadmin.pojo.dto.PageUser;
import com.easy.zadmin.pojo.entity.Course;
import com.easy.zadmin.service.CourseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author sanye
 * @Date 2023/9/2 13:35
 * @Version 1.0
 */
@RestController
public class CourseController {
    private Logger LOGGER = LoggerFactory.getLogger(CourseController.class);
    @Autowired
    private CourseService courseService;
    //创建
    @PostMapping("/course/createCourse")
    public ResponseResult createCourse(@RequestBody Course course){

        String courseName = course.getCourseName();
        if (StringUtils.isBlank(courseName)){
            return ResponseResult.COURSE_NAME_ISNOT_EMPTY;
        }
        Integer res = courseService.createCourse(course);

        return  res>0?ResponseResult.REQ_SUCCESS:ResponseResult.REQ_FAIL;

    }
    //更改
    @PostMapping("/course/updateStatus")
    public ResponseResult updateStatus(@RequestBody Course course){

        Integer res = courseService.updateCourseStatus(course);

        return  res>0?ResponseResult.REQ_SUCCESS:ResponseResult.REQ_FAIL;

    }

    //模糊查询
    @PostMapping("/course/coursePageVo")
    public ResponseResult selectPageVo(@RequestBody PageUser<Course> pageUser) {
        LOGGER.info("进入分页列表"+pageUser);
//        if(StringUtils.isBlank(pageUser.getUser().getUserName())){
//            return new ResponseResult("410","用户名");
//        }
        return new ResponseResult("200", courseService.selectPageVo(pageUser));
    }


}
