package com.itflyket.education.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itflyket.education.dto.CourseAndTeacherInfoDTO;
import com.itflyket.education.dto.CourseDTO;
import com.itflyket.education.entity.Course;
import com.itflyket.education.entity.User;
import com.itflyket.education.mapper.CourseMapper;
import com.itflyket.education.service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CourseServiceImp implements CourseService {

    @Autowired
    private CourseMapper courseMapper;

    /**
     * 查询课程信息
     * @return
     */
    @Override
    public List<CourseDTO> getCourseFields() {
        QueryWrapper<Course> queryWrapper = new QueryWrapper();
        QueryWrapper select = queryWrapper.select("id","title", "description", "rating", "category", "img", "price", "detail");
        //使用selectList查询并返回
        List<Course> courses = courseMapper.selectList(select);


        // 将 Course 列表转换为 CourseDTO 列表
        return courses.stream().map(course -> {
            CourseDTO dto = new CourseDTO();
            dto.setId(course.getId());
            dto.setTitle(course.getTitle());
            dto.setDescription(course.getDescription());
            dto.setRating(course.getRating());
            dto.setCategory(course.getCategory());
            dto.setImg(course.getImg());
            dto.setPrice(course.getPrice());
            dto.setDetail(course.getDetail());
            return dto;
        }).collect(Collectors.toList()); // 将所有 DTO 收集为列表
    }

    /**
     * 后台访问课程管理时返回的课程分页查询
     * @param currentPage
     * @param pageSize
     * @param search
     * @return
     */
    public IPage<Course> getCoursePage(Integer currentPage, Integer pageSize, String search) {
        Page<Course> page = new Page<>(currentPage, pageSize); // 创建分页对象，设置当前页和每页大小
        QueryWrapper<Course> queryWrapper = new QueryWrapper<>(); // 创建查询条件包装器

        // 如果有搜索条件，则根据用户名进行模糊查询
        if (search != null && !search.trim().isEmpty()) {
            queryWrapper.lambda().like(course -> course.getTitle(), search);
        }
        return courseMapper.selectPage(page,queryWrapper);// 执行分页查询
    }
    /**
     * 根据评分前8名去展示用户的推荐课程
     * @return
     */
    @Override
    public List<CourseDTO> findTopRatedCourse() {
        List<Course> topRatedCourse = this.courseMapper.findTopRatedCourse();
        List<CourseDTO> collect = topRatedCourse.stream().map(course -> {
            CourseDTO courseDTO = new CourseDTO();
            courseDTO.setId(course.getId());
            courseDTO.setRating(course.getRating());
            courseDTO.setDescription(course.getDescription());
            courseDTO.setTitle(course.getTitle());
            courseDTO.setCategory(course.getCategory());
            courseDTO.setImg(course.getImg());
            courseDTO.setPrice(course.getPrice());
            return courseDTO;
        }).collect(Collectors.toList());

        return collect;  // 返回转换后的 DTO 列表
    }

    /**
     * 查询课程详情的同时携带着教师的部分信息
     * @param courseId
     * @return
     */
    @Override
    public CourseAndTeacherInfoDTO selectCourseWithInstructor(Long courseId) {
        Course course = this.courseMapper.selectCourseWithInstructor(courseId);
        // 如果课程信息为空，返回空的 DTO 或者做相应处理
        if (course == null) {
            // 这里可以抛出异常或者返回一个默认的 CourseAndTeacherInfoDTO 对象
            return new CourseAndTeacherInfoDTO();  // 返回一个空对象，或者可以考虑抛出异常
        }
        //将查询的结果封装为一个CourseAndTeacherInfoDTO对象
        CourseAndTeacherInfoDTO courseAndTeacherInfoDTO = new CourseAndTeacherInfoDTO();
        courseAndTeacherInfoDTO.setId(course.getId());
        courseAndTeacherInfoDTO.setTitle(course.getTitle());
        courseAndTeacherInfoDTO.setCourseStartTime(course.getCourseStartTime());
        courseAndTeacherInfoDTO.setJoinCount(course.getJoinCount());
        courseAndTeacherInfoDTO.setRating(course.getRating());
        courseAndTeacherInfoDTO.setDescription(course.getDescription());
        courseAndTeacherInfoDTO.setCategory(course.getCategory());
        courseAndTeacherInfoDTO.setDetail(course.getDetail());
        courseAndTeacherInfoDTO.setImg(course.getImg());
        courseAndTeacherInfoDTO.setPrice(course.getPrice());

        // 如果课程中没有教师信息，避免出现空指针异常
        if (course.getTeacher() == null) {
            // 设置教师信息为空，避免后续空指针异常
            courseAndTeacherInfoDTO.setInstructorName("暂无教师"); // 默认值
            courseAndTeacherInfoDTO.setInstructorDescription("暂无描述"); // 默认值
            courseAndTeacherInfoDTO.setInstructorPhoto(""); // 默认值
            courseAndTeacherInfoDTO.setInstructorTitle("暂无职称"); // 默认值
        } else {
            // 封装教师的信息
            courseAndTeacherInfoDTO.setInstructorDescription(course.getTeacher().getDetail());
            courseAndTeacherInfoDTO.setInstructorName(course.getTeacher().getTeacherName());
            courseAndTeacherInfoDTO.setInstructorPhoto(course.getTeacher().getPhoto());
            courseAndTeacherInfoDTO.setInstructorTitle(course.getTeacher().getTitle());
        }
        return courseAndTeacherInfoDTO;
    }

}