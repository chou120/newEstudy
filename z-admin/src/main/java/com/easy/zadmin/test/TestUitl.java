//package com.easy.zadmin.test;
//
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
//
//import java.util.Collections;
//
///**
// * @Author sanye
// * @Date 2023/8/21 23:24
// * @Version 1.0
// */
//public class TestUitl {
//    public static void main(String[] args) {
//        FastAutoGenerator.create("jdbc:mysql://localhost:3306/easystudy", "root", "zhou")
//                // 全局配置
//                .globalConfig(builder -> {
//                    builder.author("sanye") // 设置作者
//                            .enableSwagger() //开启 swagger 模式
//                            .outputDir("C:\\Users\\edy\\Desktop\\ehr\\newEstudy\\z-admin");// 制定输入目录
//                })
//                // 包配置
//                .packageConfig(builder -> {
//                    builder.parent("com.demo") // 设置父包名
//                            .moduleName("") // 设置父包模块名称；
//                            // .entity("entity") // 设置Entity包名，默认值entity
//                            // .mapper("mapper") // 设置mapper包名，默认mapper
//                            .xml("C:\\Users\\edy\\Desktop\\ehr\\newEstudy\\z-admin\\src\\main\\resources\\mapper") // 设置mapper xml包名，默认mapper.xml
//                            // .service("service") // 设置service包名，默认service
//                            // .serviceImpl("service.impl") // 设置serviceImpl包名，默认service.impl
//                            //  .controller("controller") // 设置控制器包名
//                            .pathInfo(Collections.singletonMap(OutputFile.xml,"D://"));
//                })
//                // 策略配置
//                .strategyConfig(builder -> {
//                    builder
//                            //.addInclude("") // 设置需要生成的表明
//                            //.addExclude("") // 设置需要不生成的表明
//                            //.addTablePrefix("")// 设置过滤表前缀
//                            //.addTableSuffix() // 设置过滤表后缀
//                            .entityBuilder() // entity配置
//                            .enableLombok(); // 设置entity的lombok配置
//
//                })
//                // 模板引擎配置
//                .templateEngine(new FreemarkerTemplateEngine())
//                .execute();
//
//    }
//}
