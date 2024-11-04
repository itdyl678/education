package com.itflyket.education.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AxiosConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        // 配置所有路径的跨域访问
        registry.addMapping("/**") // 配置允许跨域的路径
                .allowedOriginPatterns("*")  // 允许的跨域来源
                .allowedMethods("GET", "POST", "PUT", "DELETE")  // 允许的请求方法
                .allowedHeaders("*")  // 允许的请求头
                .allowCredentials(true)  // 是否允许发送 Cookie
                .maxAge(3600);  // 设置预检请求的缓存时间（单位：秒）
    }
}
