package com.itflyket.education.controller.course;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itflyket.education.dto.CourseAndTeacherInfoDTO;
import com.itflyket.education.dto.CourseDTO;
import com.itflyket.education.entity.Course;
import com.itflyket.education.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 获取全部的课程信息
     *
     * @return
     */
    @GetMapping("/findCourseAll")
    public List<CourseDTO> getCourseFields() {
        List<CourseDTO> courseFields = courseService.getCourseFields();
        return courseFields;
    }

    /**
     * 后台访问分页逻辑
     * @param currentPage
     * @param pageSize
     * @return
     */

    @GetMapping("/getCourseAll")
    public IPage<Course> getCourseAll(@RequestParam(defaultValue = "1") Integer currentPage, // 当前页码，默认为1
                                      @RequestParam(defaultValue = "10") Integer pageSize,
                                      @RequestParam(required = false) String search) {

        System.out.println("当前页码："+currentPage + "每页展示多少数据：" +pageSize);
        return courseService.getCoursePage(currentPage,pageSize,search);
    }


    /**
     * 获取课程评分前8名的课程信息
     * @return
     */
    @GetMapping("/findTopRatedCourse")
    public List<CourseDTO> findTopRatedCourse(){
        List<CourseDTO> topRatedCourse = courseService.findTopRatedCourse();
       return topRatedCourse;
    }

    /**
     * 查看课程详细
     * @param courseId
     * @return
     */
    @GetMapping("/{courseId}")
    public CourseAndTeacherInfoDTO selectCourseWithInstructor(@PathVariable Long courseId){
       return courseService.selectCourseWithInstructor(courseId);
    }

    /**
     * 增加课程信息
     * @param course
     * @return
     */
    @PostMapping("/addCourse")
    public ResponseEntity<String>addCourse(@RequestBody Course course){
        this.courseService.addCourse(course);
        return ResponseEntity.status(HttpStatus.CREATED).body("课程添加成功"); //返回数据给前端
    }

    @PutMapping("/updateCourse/{id}")
    public ResponseEntity<String>updateCourse(@PathVariable Integer id,@RequestBody Course course){
        course.setId(id);
        int result = this.courseService.updateCourse(course);
        if (result > 0){
            return ResponseEntity.ok().body("课程修改成功");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("课程信息未找到");
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String>deleteById(@PathVariable Integer id){
        int result = courseService.deleteById(id);
        if (result > 0){
            return ResponseEntity.ok().body("删除课程信息成功！");
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("删除课程信息失败！");
        }
    }
}
