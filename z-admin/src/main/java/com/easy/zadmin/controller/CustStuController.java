package com.easy.zadmin.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.easy.zadmin.common.res.ResponseResult;
import com.easy.zadmin.pojo.dto.OrderDto;
import com.easy.zadmin.pojo.dto.PageUser;
import com.easy.zadmin.pojo.dto.RefoudCustStu;
import com.easy.zadmin.pojo.entity.*;
import com.easy.zadmin.service.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author sanye
 * @Date 2023/9/2 13:36
 * @Version 1.0
 */
@RestController
public class CustStuController {

    private Logger LOGGER = LoggerFactory.getLogger(CustStuController.class);

    @Autowired
    private CustStuService custStuService;

    @Autowired
    private ChargeRecordService chargeRecordService;

    @Autowired
    private ClassInService classInService;

    @Autowired
    private OrderService orderService;
    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @PostMapping("/customer/createCustomer")
    public ResponseResult createCustStu(@RequestBody CustStu custStu){
        if(StringUtils.isBlank(custStu.getCustName())){
            return  ResponseResult.CUSTNAME_NOT_EMPTY;//客户名称不能为空
        }
        if(StringUtils.isBlank(custStu.getCustEmail())){
            return  ResponseResult.CUSTEMAIL_NOT_EMPTY;//邮件不能为空
        }

        if(custStu.getTotal()==null){
            return  ResponseResult.CUSTERMPTY_NOT_NULL;//充值金额不能为空
        }
        return custStuService.createCustStu(custStu);
    }

    @PostMapping("/customer/editCustomer")
    public ResponseResult editCustomer(@RequestBody CustStu custStu){
        if(StringUtils.isBlank(custStu.getCustName())){
            return  ResponseResult.CUSTNAME_NOT_EMPTY;//客户名称不能为空
        }
        if(StringUtils.isBlank(custStu.getCustEmail())){
            return  ResponseResult.CUSTEMAIL_NOT_EMPTY;//邮件不能为空
        }
        return  custStuService.editCustStu(custStu);
    }



    @PostMapping("/customer/renewalOfPayment") //续费和退费
    public ResponseResult renewalOfPayment(@RequestBody RefoudCustStu refoudCustStu){

        Double total = refoudCustStu.getCustStu().getTotal();
        if(total==null){
            return ResponseResult.CUSTERMPTY_NOT_NULL;
        }

        return  custStuService.renewalOfPayment(refoudCustStu);
    }

    @PostMapping("/customer/custPageVo")
    public ResponseResult custPageVo(@RequestBody PageUser<CustStu> pageUser) {
        LOGGER.info("进入分页列表"+pageUser);

        return new ResponseResult("200", custStuService.pageUser(pageUser));
    }


    /**
     * 充值记录
     */
    @PostMapping("/customer/chargeRecord")
    public ResponseResult chargeRecord(@RequestBody PageUser<ChargeRecord> pageUser){

        return  new ResponseResult("200",chargeRecordService.selectPageVo(pageUser));
    }


    /**
     * 上课记录
     */
    @PostMapping("/customer/classInRecords")
    public ResponseResult classInRecords(@RequestBody PageUser<Order> pageUser){

        return  new ResponseResult("200",classInService.selectPageVo(pageUser));
    }
    /**
     * 获取所有老师数据
     */
    @GetMapping("/order/getUserInfo")
    public  ResponseResult   userInfos(@RequestParam(value="teaName", defaultValue="") String  teaName){

        return userService.getUserInfos(teaName);
    }

    /**
     * 获取所有的课程信息
     */
    @GetMapping("/order/getCoursesInfo")
    public  ResponseResult   courseInfos(@RequestParam(value="course", defaultValue="") String  course){
        List<Course> courseInfos = courseService.getCourseInfos(course);
        return new ResponseResult("200",courseInfos);
    }
    /**
     * 创建订单
     */
    @PostMapping("/customer/createOrder")
    public ResponseResult  createOrder(@RequestBody OrderDto orderDto){

        LOGGER.info("orderDto:"+orderDto);

        String custName_student = orderDto.getCustStu().getCustName();
        if(StringUtils.isBlank(custName_student)){
            return ResponseResult.CUSTNAME_NOT_EMPTY;//客户姓名不能为空
        }
        String userName_teacher = orderDto.getUser().getUserName();
        if(StringUtils.isBlank(userName_teacher)){
            return ResponseResult.USERNAME_NOT_NULL;//用户姓名不能为空
        }
        String courseType = orderDto.getOrder().getCourseType();
        if(StringUtils.isBlank(courseType)){
            return ResponseResult.COURSE_NAME_ISNOT_EMPTY;
        }
        Date courseTime = orderDto.getOrder().getCourseTime();//上课时间
        if(courseTime==null){
            return ResponseResult.COURSETIME_IS_NOT_NULL;
        }
        Integer courseHours = orderDto.getOrder().getCourseHours();//课时
        if(courseHours==null){
            return ResponseResult.COURSEHOURS_IS_NOT_NULL;
        }
        Double price = orderDto.getOrder().getPrice();//单价
        if(price==null){
            return ResponseResult.PRICE_IS_NULL;
        }
        return orderService.createOrder(orderDto);
    }


}
