package com.easy.zadmin.controller;

import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.easy.zadmin.common.res.ResponseResult;
import com.easy.zadmin.pojo.dto.OrderDto;
import com.easy.zadmin.pojo.dto.PageUser;
import com.easy.zadmin.pojo.entity.Course;
import com.easy.zadmin.pojo.entity.Order;
import com.easy.zadmin.pojo.entity.User;
import com.easy.zadmin.service.CourseService;
import com.easy.zadmin.service.CustStuService;
import com.easy.zadmin.service.OrderService;
import com.easy.zadmin.service.UserService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

/**
 * @Author sanye
 * @Date 2023/9/5 21:23
 * @Version 1.0
 */
@RestController
public class OrderController {


    private Logger LOGGER = LoggerFactory.getLogger(OrderController.class);

    @Autowired
    private OrderService orderService;

    @Autowired
    private CourseService courseService;

    @Autowired
    private UserService userService;

    @Autowired
    private CustStuService custStuService;

    @PostMapping("/order/createOrder")
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

    /**
     * 获取所有老师数据
     */
    @GetMapping("/order/getUserInfo")
    public  ResponseResult   userInfos(@RequestParam(value="teaName", defaultValue="") String  teaName){

        return userService.getUserInfos(teaName);
    }
    /**
     * 获取所有客户数据
     */
    @GetMapping("/order/getCustomerInfo")
    public  ResponseResult   customerInfos(@RequestParam(value="username", defaultValue="") String  username){
        List<User> custStuInfos = custStuService.getCustStuInfos(username);
        return new ResponseResult("200",custStuInfos);
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
     * 批量更新
     */
    @PostMapping("/order/orderOver") //订单完成
    public  ResponseResult updateBatch(@RequestBody List<Order> list){
        if(list.size()==0){
            return ResponseResult.COLLECTIONISNULL;
        }
        LOGGER.info("list:"+list);
        int res = orderService.updateBatch(list);
        return new ResponseResult("200",res);
    }

    /**
     * 分页
     */

    @PostMapping("/order/pageUser") //订单完成
    public  ResponseResult pageUser(@RequestBody PageUser<Order> pageUser){

        return new ResponseResult("200", orderService.pageUser(pageUser));
    }


    /**
     * 查询订单详情
     */
    @PostMapping("/order/orderDetail")
    public  ResponseResult queryOrderDetail(@RequestBody Order  order){
        ResponseResult result = orderService.queryOrder(order);
        return  new ResponseResult("200",result);
    }


    /**
     * 编辑订单   和 退单功能
     */

    @PostMapping("/order/orderOperation") //操作订单
    public   ResponseResult  orderOperation(@RequestBody OrderDto orderDto){
        LOGGER.info("orderDto:"+orderDto);
    //订单状态:0全部订单； 1进行中；2已完成；3.退单；4.异常订单
      if(orderDto.getOrder().getOrderStatus()==1){  //只有进行中的订单才可以进行编辑
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
          return orderService.editOrder(orderDto);

      }else if(orderDto.getOrder().getOrderStatus()==3){
          //退单   判断退款类型  和退款金额 不能为空
          Double rebackMoney = orderDto.getRebackDetail().getRebackMoney();
          if(rebackMoney==null){
              return ResponseResult.REBACKMONEY_NOT_NULL;
          }
          Integer rebackType = orderDto.getRebackDetail().getRebackType();
          if(rebackType==null){
              return ResponseResult.REBACKTYPE_NOT_NULL;
          }
          //退单
          return   orderService.rebackOrder(orderDto);
      }
      return ResponseResult.OPRETION_INVALID;
    }

}
