//package com.nwpu.carpoolingsystem.config;
//
//import org.springframework.context.annotation.Configuration;
//import org.springframework.web.servlet.config.annotation.CorsRegistry;
//import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
//
//@Configuration
//public class WebConfig implements WebMvcConfigurer {
//
//    @Override
//    public void addCorsMappings(CorsRegistry registry) {
//        registry.addMapping("/**")  // 允许所有请求路径跨域
//                .allowedOrigins("*")  // 允许所有源跨域访问
//                .allowCredentials(false)  // 允许发送凭证（如 Cookies）
//                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS", "HEAD")  // 允许的 HTTP 方法
//                .allowedHeaders("*")  // 允许所有请求头
//                .exposedHeaders("Server","Content-Length", "Authorization", "Access-Token", "Access-Control-Allow-Origin","Access-Control-Allow-Credentials");  // 暴露哪些头部信息
//    }
//}

