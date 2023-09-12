package com.easy.zadmin.handler;

import com.alibaba.fastjson.JSON;
import com.easy.zadmin.common.res.ResponseResult;
import com.easy.zadmin.filter.JwtAuthenticationTokenFilter;
import com.easy.zadmin.util.WebUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @Author sanye
 * @Date 2023/8/19 19:51
 * @Version 1.0
 */

@Component
public class AccessDeniedHandlerImpl implements AccessDeniedHandler {

    private Logger LOGGER = LoggerFactory.getLogger(AccessDeniedHandlerImpl.class);

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {

        LOGGER.info("AccessDeniedHandlerImpl..."+request);

        ResponseResult result = new ResponseResult("403", "权限不足");
        String json = JSON.toJSONString(result);
        WebUtils.renderString(response,json);

    }
}

