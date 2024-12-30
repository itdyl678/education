package com.itflyket.education.entity;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDateTime;

/**
 * 聊天历史实体表
 */
@TableName("chatHistory")
public class ChatHistory {
    private Integer id;
//    @TableField("user_id")  已经配置了驼峰命名规则
    private Integer userId;
    private String username;
    private String question;
    private String answer;
//    @TableField("created_at")
    private LocalDateTime createdAt;


    public ChatHistory() {
    }

    public ChatHistory(Integer id, Integer userId, String username, String question, String answer, LocalDateTime createdAt) {
        this.id = id;
        this.userId = userId;
        this.username = username;
        this.question = question;
        this.answer = answer;
        this.createdAt = createdAt;
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
     * @return username
     */
    public String getUsername() {
        return username;
    }

    /**
     * 设置
     * @param username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * 获取
     * @return question
     */
    public String getQuestion() {
        return question;
    }

    /**
     * 设置
     * @param question
     */
    public void setQuestion(String question) {
        this.question = question;
    }

    /**
     * 获取
     * @return answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 设置
     * @param answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * 获取
     * @return createdAt
     */
    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置
     * @param createdAt
     */
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String toString() {
        return "ChatHistory{id = " + id + ", userId = " + userId + ", username = " + username + ", question = " + question + ", answer = " + answer + ", createdAt = " + createdAt + "}";
    }
}
