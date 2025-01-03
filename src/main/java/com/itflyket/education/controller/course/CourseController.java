package com.itflyket.education.controller.course;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itflyket.education.dto.CourseAndTeacherInfoDTO;
import com.itflyket.education.dto.CourseDTO;
import com.itflyket.education.entity.Course;
import com.itflyket.education.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
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

    @GetMapping("/{courseId}")
    public CourseAndTeacherInfoDTO selectCourseWithInstructor(@PathVariable Long courseId){
       return courseService.selectCourseWithInstructor(courseId);
    }
}
