package com.itflyket.education.controller.video;

import com.itflyket.education.entity.VideoRecords;
import com.itflyket.education.service.VideoRecordsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.text.SimpleDateFormat;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

@RestController
@RequestMapping("/save-video")

public class VideoController {

    @Autowired
    private VideoRecordsService videoRecordsService;

    // 声明一个静态的AtomicInteger计数器，避免同一天上传多个视频文件时文件名重复
    private static final AtomicInteger counter = new AtomicInteger(0);
    @PostMapping
    public String saveVideo(@RequestParam("file") MultipartFile file,
                            @RequestParam("userId") Long userId) {
        try {
            if (file.isEmpty()) {
                return "接收到的文件为空";
            }
            System.out.println("接收到的文件大小：" + file.getSize());
            // 定义保存目录
            String uploadDir = "D:\\Idea_study\\video\\";  // 修改为你想存储的路径

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

            // 保存视频信息到数据库
            VideoRecords videoRecord = new VideoRecords();
            videoRecord.setVideoName(fileName);
            videoRecord.setVideoUrl(savePath);
            videoRecord.setUserId(userId);
            videoRecord.setCreateTime(new Date());
            videoRecord.setSize(file.getSize());
            videoRecordsService.save(videoRecord);

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

    @GetMapping("/videos")
    public List<String> getVideos() {

        // 获取数据库中的所有视频记录
        List<VideoRecords> videoRecords = videoRecordsService.list();

        List<String> videoUrls = new ArrayList<>();
        if (videoRecords != null && !videoRecords.isEmpty()) {
            for (VideoRecords record : videoRecords) {
                // 拼接虚拟路径，返回给前端
                videoUrls.add("/videos/" + record.getVideoName());
            }
        }

        return videoUrls;
    }

}
