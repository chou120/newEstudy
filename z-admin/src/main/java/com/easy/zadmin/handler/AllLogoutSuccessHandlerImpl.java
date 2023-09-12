package com.easy.zadmin.handler;

import com.alibaba.fastjson.JSONObject;
import com.easy.zadmin.common.res.ResponseResult;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author sanye
 * @Date 2023/8/20 10:08
 * @Version 1.0
 */
@Component
public class AllLogoutSuccessHandlerImpl implements LogoutSuccessHandler {

    private Logger LOGGER = LoggerFactory.getLogger(AllLogoutSuccessHandlerImpl.class);

    @Override
    public void onLogoutSuccess(HttpServletRequest httpServletRequest, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        // 当认证成功后，响应 JSON 数据给前端
        LOGGER.info("进入onLogoutSuccess退出成功...");
        final ResponseResult r = new ResponseResult("200","退出成功");
        final String jsonString = JSONObject.toJSONString(r);
        response.setContentType("application/json;charset=utf-8");
        response.getWriter().write(jsonString);
    }
}
