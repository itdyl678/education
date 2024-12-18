package com.itflyket.education.controller.teacher;

import com.itflyket.education.entity.Teacher;
import com.itflyket.education.result.ResponseResult;
import com.itflyket.education.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    @GetMapping("/getTeacherAll")
    public ResponseResult getTeacherAll(){
        System.out.println("教师控制台");
        try {
            List<Teacher> allTeacher = teacherService.getAllTeacher();
            return ResponseResult.success("获取教师列表成功").data("teacher",allTeacher);
        }catch (Exception e){
            return ResponseResult.fail("获取教师信息失败:" + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseResult gerTeacherDetails(@PathVariable Integer id){
        try {
            Teacher teacherDetail = teacherService.getTeacherDetail(id);
            System.out.println("教师："+teacherDetail);
            if (teacherDetail != null){
                return ResponseResult.success("获取教师详情成功").data("teacher",teacherDetail);
            }else {
                return ResponseResult.fail("教师未找到");
            }
        }catch (Exception e){
     return ResponseResult.fail("获取教师详情失败" + e.getMessage());
        }
    }
}
