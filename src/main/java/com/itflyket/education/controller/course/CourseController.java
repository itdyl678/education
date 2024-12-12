package com.itflyket.education.controller.course;

import com.itflyket.education.dto.CourseDTO;
import com.itflyket.education.entity.Course;
import com.itflyket.education.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/findCourseAll")
    public List<CourseDTO> getCourseFields(){
        List<CourseDTO> courseFields = courseService.getCourseFields();
        System.out.println("打印的是findCourseAll里面的数据：+++"+courseFields);
        return courseFields;
    }
}
