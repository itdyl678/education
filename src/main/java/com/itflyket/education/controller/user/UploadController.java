package com.itflyket.education.controller.user;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UploadController {

    @PostMapping("/upload/avatar")
    public Map<String, Object> uploadAvatar(@RequestParam("file") MultipartFile file) {
        Map<String, Object> result = new HashMap<>();

        System.out.println("-------------------进入头像修改的控制层-----------------------");
        // 检查文件是否为空
        if (file.isEmpty()) {
            result.put("code", 1);
            result.put("message", "文件为空");
            return result;
        }

        try {
            // 指定保存路径
            String uploadDir = "D:/uploads/avatars/";
            File uploadFolder = new File(uploadDir);

            // 如果保存目录不存在，则创建它
            if (!uploadFolder.exists()) {
                uploadFolder.mkdirs(); // 递归创建目录
            }

            // 生成唯一的文件名，避免文件名冲突
            String fileName = System.currentTimeMillis() + "_" + file.getOriginalFilename();

            // 构造保存文件的完整路径
            File dest = new File(uploadDir + fileName);

            // 保存文件到本地
            file.transferTo(dest);

            // 假设通过静态资源映射，生成可以访问的 URL
            String fileUrl = "http://localhost:8089/avatars/" + fileName;

            System.out.println("前端上传的图片地址："+fileUrl);
            result.put("code", 0);
            result.put("url", fileUrl);
            result.put("message", "文件上传成功");
        } catch (IOException e) {
            e.printStackTrace();
            result.put("code", 1);
            result.put("message", "文件上传失败：" + e.getMessage());
        }

        return result;
    }
}
