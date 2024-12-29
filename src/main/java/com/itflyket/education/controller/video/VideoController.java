package com.itflyket.education.controller.video;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;

import java.util.Date;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/save-video")
@CrossOrigin(origins = "http://localhost:8080/live") // 替换为前端地址
public class VideoController {
    // 声明一个静态的AtomicInteger计数器，避免同一天上传多个视频文件时文件名重复
    private static final AtomicInteger counter = new AtomicInteger(0);
    @PostMapping
    public String saveVideo(@RequestParam("file") MultipartFile file) {
        try {
            if (file.isEmpty()) {
                return "接收到的文件为空";
            }
            System.out.println("接收到的文件大小：" + file.getSize());
            // 定义保存目录
            String uploadDir = "D:\\Idea_study\\gitee\\education\\video\\";  // 修改为你想存储的路径

            // 获取当前日期，格式化为 "yyyy-MM-dd"
            String dateStamp = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

            // 获取文件的扩展名
            String fileExtension = getFileExtension(file.getOriginalFilename());

            // 使用日期和编号生成唯一的文件名
            String fileName = dateStamp + "-" + counter.incrementAndGet() + fileExtension;

            // 完整保存路径
            String savePath = uploadDir + fileName;

            // 创建文件并保存
            File destinationFile = new File(savePath);
            file.transferTo(destinationFile);

            return "视频保存成功，文件名：" + fileName;
        } catch (Exception e) {
            e.printStackTrace();
            return "视频保存失败";
        }
    }

    // 获取文件的扩展名
    private String getFileExtension(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index > 0) {
            return fileName.substring(index);  // 返回文件的扩展名，包括点
        }
        return "";  // 如果没有扩展名，返回空字符串
    }

}
