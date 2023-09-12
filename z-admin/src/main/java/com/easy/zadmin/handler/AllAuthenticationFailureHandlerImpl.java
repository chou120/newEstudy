package com.easy.zadmin.handler;

import com.alibaba.fastjson.JSONObject;
import com.easy.zadmin.common.res.ResponseResult;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author sanye
 * @Date 2023/8/20 10:07
 * @Version 1.0
 */
@Component
public class AllAuthenticationFailureHandlerImpl  implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest httpServletRequest, HttpServletResponse response, AuthenticationException e) throws IOException, ServletException {
        // 当认证成功后，响应 JSON 数据给前端
        final ResponseResult r = new ResponseResult("401","认证失败");
        final String jsonString = JSONObject.toJSONString(r);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
