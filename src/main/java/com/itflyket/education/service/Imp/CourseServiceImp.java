package com.itflyket.education.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itflyket.education.dto.CourseDTO;
import com.itflyket.education.entity.Course;
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
}