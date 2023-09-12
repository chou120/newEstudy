package com.easy.zadmin.common.res;

import com.fasterxml.jackson.annotation.JsonInclude;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {

    public static final String SUCCESS = "200";

    /**
     * 通用使用错误码
     **/
    public static final ResponseResult REQ_SUCCESS = new ResponseResult("200", "请求成功");
    public static final ResponseResult SYS_ERROR = new ResponseResult("500", "网络繁忙,请稍后再试");
    public static final ResponseResult ARGS_ERROR = new ResponseResult("400", "参数错误");
    public static final ResponseResult SYS_ARGS_ERROR = new ResponseResult("401", "系统参数错误");

    /**
     * 外部接口返回的状态码
     */
    public static final ResponseResult TOKEN_ISNULL  = new ResponseResult("300", "请求头token为空");
    public static final ResponseResult MERCH_API_CORPID_IS_NULL  = new ResponseResult("301", "");
    public static final ResponseResult TOKEN_FAILURE  = new ResponseResult("302", "token失效或不存在");
    public static final ResponseResult TOKEN_INVALID = new ResponseResult("303", "token无效");
    public static final ResponseResult OPRETION_INVALID = new ResponseResult("304", "非法操作");

    /**
     * 登录退出状态码
     */
    public static final ResponseResult LOGIN_OUT  = new ResponseResult("204", "退出成功");
    public static final ResponseResult USER_MOBILE_NOT_EMPTY = new ResponseResult("510", "用户手机号不能为空！");
    public static final ResponseResult USER_MOBILE_IS_TRUE = new ResponseResult("530", "请输入正确的手机号！");
    public static final ResponseResult USER_PASSWORD_NOT_EMPTY = new ResponseResult("511", "用户密码不能为空！");
    public static final ResponseResult USER_VCODE_NOT_EMPTY = new ResponseResult("512", "验证码不能为空！");
    public static final ResponseResult USER_PASSWORD_SATISFY = new ResponseResult("513", "密码必须大小写开头,且长度为8~16位！");
    public static final ResponseResult USER_EDIT_FAILED = new ResponseResult("514", "用户更新失败！");
    public static final ResponseResult USER_EXISTENCE = new ResponseResult("515", "用户已邮箱已被使用！");
    public static final ResponseResult SMS_TYPE_NOT_EMPTY = new ResponseResult("516", "验证码类型不能为空！");
    public static final ResponseResult SMS_CODE_SEND_FAILED = new ResponseResult("517", "短信验证码发送失败！");
    public static final ResponseResult SMS_CODE_EXPIRE = new ResponseResult("518", "短信验证码已失效！");
    public static final ResponseResult USER_RESET_PASSWORD_FAILED = new ResponseResult("519", "重置密码失败！");
    public static final ResponseResult USER_CREATE_FAILED = new ResponseResult("520", "用户注册失败！");
    public static final ResponseResult USER_GET_FAILED = new ResponseResult("521", "用户获取失败！");
    public static final ResponseResult USER_QUERY_FAILED = new ResponseResult("522", "用户列表获取失败");
    public static final ResponseResult USER_NOT_EXISTENT = new ResponseResult("523", "用户不存在");
    public static final ResponseResult USER_MOBILE_EXISTENT = new ResponseResult("524", "用户手机号已存在！");
    public static final ResponseResult ROLE_NAME_EXISTENT = new ResponseResult("525", "角色名称已存在！");
    public static final ResponseResult ROLE_EXISTENT_USER = new ResponseResult("525", "角色下还有用户存在，不能删除！");
    public static final ResponseResult DEL_USER_RES = new ResponseResult("540", "用户删除失败");
    public static final ResponseResult USERID_NOTEXISTS = new ResponseResult("541", "用户id不存在");
    public static final ResponseResult USERID_NOT_NULL = new ResponseResult("542", "用户id不能为空");
    public static final ResponseResult USERNAME_NOT_NULL = new ResponseResult("543", "用户名不能为空");
    public static final ResponseResult USER_EMAIL_NOT_NULL = new ResponseResult("544", "邮箱不能为空");
    public static final ResponseResult EMAIL_FORMAT_NOT_TRUE = new ResponseResult("545", "邮箱格式不正确");
    public static final ResponseResult EDIT_USER_RES = new ResponseResult("546", "账户状态更改成功");
    public static final ResponseResult STATUS_NULL = new ResponseResult("547", "账号状态不存在");
    public static final ResponseResult USER_EMAIL_NOT_EXIST = new ResponseResult("548", "该邮箱还未被注册");
    public static final ResponseResult PASSWORD_SEND_TO_EMAIL= new ResponseResult("549", "新密码已经重新发送至邮箱");
    public static final ResponseResult CUSTNAME_NOT_EMPTY= new ResponseResult("550", "客户名不能为空");
    public static final ResponseResult CUSTEMAIL_NOT_EMPTY= new ResponseResult("551", "客户邮箱不能为空");
    public static final ResponseResult CUSTERMPTY_NOT_NULL= new ResponseResult("552", "金额不能为空");
    public static final ResponseResult CUSTNAME_REPETITION= new ResponseResult("553", "客户已存在");
    public static final ResponseResult CRETAECUST_FAIL= new ResponseResult("554", "创建客户失败");
    public static final ResponseResult REQ_FAIL= new ResponseResult("555", "操作失败");
    public static final ResponseResult COURSE_NAME_ISNOT_EMPTY= new ResponseResult("556", "辅导类型不能为空");
    public static final ResponseResult PLEASE_TO_TOP_UP= new ResponseResult("557", "当前余额不足");
    public static final ResponseResult COURSETIME_IS_NOT_NULL= new ResponseResult("558", "开始时间不能为空");
    public static final ResponseResult COURSEHOURS_IS_NOT_NULL= new ResponseResult("559", "课时必填");
    public static final ResponseResult PRICE_IS_NULL= new ResponseResult("559", "单价必填");
    public static final ResponseResult REBACKMONEY_NOT_NULL= new ResponseResult("560", "退款金额不能为空");
    public static final ResponseResult REBACKTYPE_NOT_NULL= new ResponseResult("561", "退款类型不能为空");
    public static final ResponseResult COLLECTIONISNULL= new ResponseResult("562", "请选择要完成的订单");

    /**
     * 状态码
     */
    private String code;
    /**
     * 提示信息，如果有错误时，前端可以获取该字段进行提示
     */
    private String msg;
    /**
     * 查询到的结果数据，
     */
    private T data;

    public ResponseResult(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public ResponseResult(String code, T data) {
        this.code = code;
        this.data = data;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public ResponseResult(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
