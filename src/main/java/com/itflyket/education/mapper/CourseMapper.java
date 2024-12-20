package com.itflyket.education.mapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itflyket.education.entity.Course;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 继承BaseMapper，使用里面的方法进行查询
 */
public interface CourseMapper extends BaseMapper<Course> {

    //筛选评分前8名的课程作为推荐课程传递给前端
    @Select("select * from courses order by rating desc LIMIT 8")
    List<Course> findTopRatedCourse();

    //点击具体的课程详情页面的同时携带者教师的个人部分信息
//    @Select("select c.*,t.id as tId,t.teacherName,t.subject," +
//            "t.photo,t.detail,t.achievements " +
//            "from courses c,teachers t where c.id = #{courseId} and c.teacherId=t.id")
    Course selectCourseWithInstructor(Long courseId);
}
