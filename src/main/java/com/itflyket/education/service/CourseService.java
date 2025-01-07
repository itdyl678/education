package com.itflyket.education.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.itflyket.education.dto.CourseAndTeacherInfoDTO;
import com.itflyket.education.dto.CourseDTO;
import com.itflyket.education.entity.Course;
import com.itflyket.education.entity.User;

import java.util.List;

public interface CourseService {
    //查询指定的课程信息
    List<CourseDTO> getCourseFields();

    //将课程评分前8名作为推荐课程传递给前端去展示
    List<CourseDTO> findTopRatedCourse();

    ////点击具体的课程详情页面的同时携带者教师的个人部分信息
    CourseAndTeacherInfoDTO selectCourseWithInstructor(Long courseId);

    //后台课程分页功能
    IPage<Course> getCoursePage(Integer currentPage, Integer pageSize, String search);

    //添加课程信息
    void addCourse(Course course);

    //修改课程信息
    int updateCourse(Course course);

    //删除课程信息
    int deleteById(Integer id);
}
