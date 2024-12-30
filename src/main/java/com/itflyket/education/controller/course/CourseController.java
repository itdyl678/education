package com.itflyket.education.controller.course;

import com.itflyket.education.dto.CourseAndTeacherInfoDTO;
import com.itflyket.education.dto.CourseDTO;
import com.itflyket.education.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    /**
     * 获取全部的课程信息
     * @return
     */
    @GetMapping("/findCourseAll")
    public List<CourseDTO> getCourseFields(){
        List<CourseDTO> courseFields = courseService.getCourseFields();
        return courseFields;
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
