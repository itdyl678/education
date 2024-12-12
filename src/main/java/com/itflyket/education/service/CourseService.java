package com.itflyket.education.service;

import com.itflyket.education.dto.CourseDTO;

import java.util.List;

public interface CourseService {
    //查询指定的课程信息
    List<CourseDTO> getCourseFields();
}
