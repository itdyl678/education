package com.itflyket.education.service;

import com.itflyket.education.entity.Teacher;

import java.util.List;

public interface TeacherService {
    /**
     * 获取所用的教师信息
     * @return
     */
    List<Teacher> getAllTeacher();

    /**
     * 更具教师的id获取对应的详细用户数据
     * @param teacherId
     * @return
     */
    Teacher getTeacherDetail(Integer teacherId);
}
