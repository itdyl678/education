package com.itflyket.education.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itflyket.education.entity.Comment;

import java.util.List;

// 继承 BaseMapper，MyBatis-Plus 自动提供常用的增删改查方法
public interface CommentMapper extends BaseMapper<Comment> {

    /**
     * 获取某个老师的所有评论以及对应的用户信息
     * @param teacherId
     * @return
     */
    List<Comment> getCommentsWithUserInfo(Integer teacherId);
}
