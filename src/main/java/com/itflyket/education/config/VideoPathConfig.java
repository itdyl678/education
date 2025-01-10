package com.itflyket.education.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class VideoPathConfig implements WebMvcConfigurer {

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // 映射虚拟路径 /videos/** 到本地目录 D:/Idea_study/gitee/education/video/
        registry.addResourceHandler("/videos/**")
                .addResourceLocations("file:D:/Idea_study/video/");
    }
}