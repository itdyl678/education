package com.itflyket.education.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.itflyket.education.dto.UserCommentDTO;

import java.time.LocalDateTime;
import java.util.Date;

@TableName("Comments")
public class Comment {
    private Integer id;
    @TableField("userId")
    private Integer userId;
    @TableField("teacherId")
    private Integer teacherId;
    private String comment;
    private double rating;
    @TableField("createAt")
    private LocalDateTime createAt;
    @TableField("updatedAt")
    private Date updatedAt;

    @TableField(exist = false)
    private UserCommentDTO userCommentDTO;


    public Comment() {
    }

    public Comment(Integer id, Integer userId, Integer teacherId, String comment, double rating, LocalDateTime createAt, Date updatedAt, UserCommentDTO userCommentDTO) {
        this.id = id;
        this.userId = userId;
        this.teacherId = teacherId;
        this.comment = comment;
        this.rating = rating;
        this.createAt = createAt;
        this.updatedAt = updatedAt;
        this.userCommentDTO = userCommentDTO;
    }

    /**
     * 获取
     * @return id
     */
    public Integer getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Integer id) {
        this.id = id;
    }

    /**
     * 获取
     * @return userId
     */
    public Integer getUserId() {
        return userId;
    }

    /**
     * 设置
     * @param userId
     */
    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    /**
     * 获取
     * @return teacherId
     */
    public Integer getTeacherId() {
        return teacherId;
    }

    /**
     * 设置
     * @param teacherId
     */
    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    /**
     * 获取
     * @return comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * 设置
     * @param comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * 获取
     * @return rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * 设置
     * @param rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * 获取
     * @return createAt
     */
    public LocalDateTime getCreateAt() {
        return createAt;
    }

    /**
     * 设置
     * @param createAt
     */
    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    /**
     * 获取
     * @return updatedAt
     */
    public Date getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 设置
     * @param updatedAt
     */
    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    /**
     * 获取
     * @return userCommentDTO
     */
    public UserCommentDTO getUserCommentDTO() {
        return userCommentDTO;
    }

    /**
     * 设置
     * @param userCommentDTO
     */
    public void setUserCommentDTO(UserCommentDTO userCommentDTO) {
        this.userCommentDTO = userCommentDTO;
    }

    public String toString() {
        return "Comment{id = " + id + ", userId = " + userId + ", teacherId = " + teacherId + ", comment = " + comment + ", rating = " + rating + ", createAt = " + createAt + ", updatedAt = " + updatedAt + ", userCommentDTO = " + userCommentDTO + "}";
    }
}
