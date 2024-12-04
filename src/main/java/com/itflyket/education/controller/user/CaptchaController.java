package com.itflyket.education.controller.user;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Random;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * 验证码生成
 */
@RestController
@RequestMapping("/captcha")
public class CaptchaController {

    @Autowired
    private DefaultKaptcha captchaProducer; // Kaptcha 验证码生成器

    @Autowired
    private RedisTemplate<String, String> redisTemplate; // Redis 用于存储验证码答案

    /**
     * 生成带运算的验证码
     */
    @GetMapping("/generate")
    public void generateCaptcha(HttpServletResponse response) throws IOException {
        // 1. 随机生成运算符和操作数
        Random random = new Random();
        int num1 = random.nextInt(10); // 生成0到9之间的随机数
        int num2 = random.nextInt(10); // 生成0到9之间的随机数
        String operator = random.nextInt(2) == 0 ? "+" : "-"; // 随机选择 "+" 或 "-"
        String question = num1 + " " + operator + " " + num2;

        // 2. 计算运算结果
        int answer = operator.equals("+") ? num1 + num2 : num1 - num2;

        // 3. 生成唯一的验证码 ID
        String captchaId = UUID.randomUUID().toString();

        // 4. 将答案存储到 Redis，设置 5 分钟过期时间
        redisTemplate.opsForValue().set("CAPTCHA_" + captchaId, String.valueOf(answer), 5, TimeUnit.MINUTES);

        // 5. 使用 Kaptcha 生成问题图片
        BufferedImage captchaImage = captchaProducer.createImage(question);

        // 6. 将图片写入 HTTP 响应
        response.setHeader("Captcha-ID", captchaId); // 返回 captchaId 给前端
        response.setHeader("Access-Control-Expose-Headers", "Captcha-ID"); // 允许前端访问Captcha-ID头部
        response.setContentType("image/jpeg");
        ImageIO.write(captchaImage, "jpeg", response.getOutputStream());
    }

}
