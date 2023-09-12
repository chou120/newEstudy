package com.easy.zadmin.config.cors;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Author sanye
 * @Date 2023/8/19 20:00
 * @Version 1.0
 */

@Configuration
public class CorsConfig implements WebMvcConfigurer {
    /**
     * 浏览器出于安全的考虑，使用 XMLHttpRequest对象发起 HTTP请求时必须遵守同源策略，
     * 否则就是跨域的HTTP请求，默认情况下是被禁止的。 同源策略要求源相同才能正常进行通信，即协议、域名、端口号都完全一致。
     * 前后端分离项目，前端项目和后端项目一般都不是同源的，所以肯定会存在跨域请求的问题。
     * 所以我们就要处理一下，让前端能进行跨域请求
     *
     * @param registry
     */

    @Override
    public void addCorsMappings(CorsRegistry registry) {

        // 设置允许跨域的路径
        registry.addMapping("/**")
                // 设置允许跨域请求的域名
                .allowedOrigins("*")
                // 是否允许cookie
                .allowCredentials(true)
                // 设置允许的请求方式
                .allowedMethods("*")
                // 设置允许的header属性
                .allowedHeaders("*")
                // 跨域允许时间
                .maxAge(3600);
    }
}



