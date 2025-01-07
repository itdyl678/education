package com.itflyket.education.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itflyket.education.dto.UserCommentDTO;
import com.itflyket.education.entity.Comment;
import com.itflyket.education.entity.Teacher;
import com.itflyket.education.mapper.CommentMapper;
import com.itflyket.education.mapper.TeacherMapper;
import com.itflyket.education.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class TeacherServiceImp implements TeacherService {

    @Autowired
    private TeacherMapper teacherMapper;

    @Autowired
    private CommentMapper commentMapper;

    @Override
    /**
     * 获取所有的用户信息
     */
    public List<Teacher> getAllTeacher() {
        return teacherMapper.selectList(null);
    }

    /**
     * 获取老师的详细信息，包括所有的评论，用户以及的头像，姓名等部分信息
     * @param teacherId
     * @return
     */
    @Override
    public Teacher getTeacherDetail(Integer teacherId) {
        Teacher teacher = teacherMapper.selectById(teacherId); //查询教师信息
        if (teacher != null) {
            List<Comment> commentsWithUserInfo = this.commentMapper.getCommentsWithUserInfo(teacherId);
            //将评论中的用户信息转换为UserCommentDTO
            for (Comment comment : commentsWithUserInfo){
                if (comment.getUserCommentDTO() != null){
                    UserCommentDTO userCommentDTO = new UserCommentDTO();
                    userCommentDTO.setId(comment.getUserCommentDTO().getId());
                    userCommentDTO.setAvatar(comment.getUserCommentDTO().getAvatar());
                    userCommentDTO.setUsername(comment.getUserCommentDTO().getUsername());
                    comment.setUserCommentDTO(userCommentDTO); //转换为UserCommentDTO
                }
            }
            teacher.setComments(commentsWithUserInfo);  //将评论设置到教师对象中
        }
        return teacher;
    }

    /**
     * 增加教师信息
     * @param teacher
     * @return
     */
    @Override
    public int addTeacher(Teacher teacher) {
        teacher.setCreatedTime(new Date()); //获取本地操作系统的时间
        return this.teacherMapper.insert(teacher);
    }

    @Override
    public int deleteById(Integer id) {
        return this.teacherMapper.deleteById(id);
    }

    /**
     * 修改教师信息
     * @param teacher
     * @return
     */
    @Override
    public int updateTeacher(Teacher teacher) {
        return this.teacherMapper.updateById(teacher);
    }
}
