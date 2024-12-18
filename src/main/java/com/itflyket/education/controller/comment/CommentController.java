package com.itflyket.education.controller.comment;

import com.itflyket.education.entity.Comment;
import com.itflyket.education.result.ResponseResult;
import com.itflyket.education.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @PostMapping("/saveComments")
    public ResponseResult saveComments(@RequestBody Comment comment){

        try {
            //保存评论
            Comment saveComment = this.commentService.saveComment(comment);
            // 返回成功的响应，附带数据
            return ResponseResult.success("评论提交成功").data("comment",saveComment);
        }catch (Exception e){
          return ResponseResult.fail("评论提交失败" + e.getMessage());
        }
    }
}
