package com.itflyket.education.service.Imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itflyket.education.entity.Comment;
import com.itflyket.education.mapper.CommentMapper;
import com.itflyket.education.service.CommentService;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class CommentServiceImp extends ServiceImpl<CommentMapper,Comment> implements CommentService {
    /**
     * 保存用户的评论
     * @param comment
     * @return
     */
    @Override
    public Comment saveComment(Comment comment) {
        comment.setCreateAt(LocalDateTime.now());  // 设置评论时间为当前时间
        save(comment);  // 使用 MyBatis-Plus 的 save 方法，自动处理 id 自增
        return comment;
    }
}
