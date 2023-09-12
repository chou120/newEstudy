package com.easy.zadmin.handler;

import com.alibaba.fastjson.JSONObject;
import com.easy.zadmin.common.res.ResponseResult;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.FilterChain;
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
public class AllAuthenticationSuccessHandlerImpl  implements AuthenticationSuccessHandler {
    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException, ServletException {
     //   AuthenticationSuccessHandler.super.onAuthenticationSuccess(request, response, chain, authentication);

        // 当认证成功后，响应 JSON 数据给前端
        final ResponseResult r = new ResponseResult("200","认证成功");
        final String jsonString = JSONObject.toJSONString(r);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonString);

    }

    @Override
    public void onAuthenticationSuccess(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Authentication authentication) throws IOException, ServletException {

    }
}
