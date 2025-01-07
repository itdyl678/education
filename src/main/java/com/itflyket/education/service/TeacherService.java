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
     * 更具教师的id获取对应的详细教师数据
     * @param teacherId
     * @return
     */
    Teacher getTeacherDetail(Integer teacherId);

    /**
     * 增加教师信息
     * @param teacher
     * @return
     */
    int addTeacher(Teacher teacher);

    /**
     * 根据教师id删除教师信息
     * @param id
     * @return
     */
    int deleteById(Integer id);

    /**
     * 修改教师信息
     * @param teacher
     * @return
     */
    int updateTeacher(Teacher teacher);
}
