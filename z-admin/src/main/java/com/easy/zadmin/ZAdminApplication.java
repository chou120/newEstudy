package com.easy.zadmin;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
/**
 * 配置mapper扫描
 */
@MapperScan("com.easy.zadmin.mapper")
//开启授权操作
@EnableGlobalMethodSecurity(prePostEnabled = true)

public class ZAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(ZAdminApplication.class, args);
    }
}
