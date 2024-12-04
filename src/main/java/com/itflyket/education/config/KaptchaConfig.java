package com.itflyket.education.config;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import com.google.code.kaptcha.util.Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Properties;

/**
 * 生成验证码图片
 */
@Configuration
public class KaptchaConfig {

    @Bean
    public DefaultKaptcha producer() {
        Properties properties = new Properties();

        // 验证码图片样式配置
        properties.setProperty("kaptcha.border", "no"); // 不显示边框
        properties.setProperty("kaptcha.textproducer.font.color", "black"); // 字体颜色
        properties.setProperty("kaptcha.image.width", "150"); // 图片宽度
        properties.setProperty("kaptcha.image.height", "50"); // 图片高度
        properties.setProperty("kaptcha.textproducer.font.size", "40"); // 字体大小
        properties.setProperty("kaptcha.noise.impl", "com.google.code.kaptcha.impl.NoNoise"); // 无噪点

        DefaultKaptcha defaultKaptcha = new DefaultKaptcha();
        defaultKaptcha.setConfig(new Config(properties));
        return defaultKaptcha;
    }
}
