package com.itflyket.education.controller.teacher;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itflyket.education.entity.Teacher;
import com.itflyket.education.result.ResponseResult;
import com.itflyket.education.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 获取教师的全部信息
     * @return
     */
    @GetMapping("/getTeacherAll")
    public ResponseResult getTeacherAll(){
        System.out.println("教师控制台");
        try {
            List<Teacher> allTeacher = teacherService.getAllTeacher();
            int teacherSize = allTeacher.size();  //计算总得数量
            return ResponseResult.success("获取教师列表成功").data("teacher",allTeacher).data("total",teacherSize);
        }catch (Exception e){
            return ResponseResult.fail("获取教师信息失败:" + e.getMessage());
        }
    }

    /**
     * 后台获取教师分页逻辑代码
     * @param currentPage
     * @param pageSize
     * @param search
     * @return
     */
    @GetMapping("/getTeachersAll")
    public ResponseEntity<?> getTeacherAll(@RequestParam(defaultValue = "1") Integer currentPage, // 当前页码，默认为1
                                           @RequestParam(defaultValue = "5") Integer pageSize,
                                           @RequestParam(required = false) String search){
        IPage<Teacher> teacherAll = this.teacherService.getTeacherAll(currentPage, pageSize, search);
        if (teacherAll != null){
            return ResponseEntity.ok().body(teacherAll);
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("教师信息返回失败！");
        }
    }
    /**
     * 根据id获取教师的详细信息
     * @param id
     * @return
     */
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

    /**
     * 添加教师信息
     * @param teacher
     * @return
     */
    @PostMapping("/addTeacher")
    public ResponseEntity<String> addTeacher(@RequestBody Teacher teacher){
        int result = this.teacherService.addTeacher(teacher);
        if (result > 0){
            return ResponseEntity.ok().body("教师信息添加成功");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("教师信息添加失败！");
        }
    }

    /**
     * 删除教师信息
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable Integer id){
        System.out.println("教师id：-----"+id);
        int result = this.teacherService.deleteById(id);
        if (result > 0) {
            return ResponseEntity.ok().body("教师信息删除成功！");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("教师信息删除失败");
        }
    }

    /**
     * 修改教师信息
     * @param id
     * @param teacher
     * @return
     */
    @PutMapping("/updateTeacher/{id}")
    public ResponseEntity<String> updateTeacher(@PathVariable Integer id,@RequestBody Teacher teacher){
        teacher.setId(id);
        int result = this.teacherService.updateTeacher(teacher);
        if (result > 0){
            return ResponseEntity.ok().body("教师信息修改成功");
        }else {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("教师信息修改失败！");

        }
    }
}
