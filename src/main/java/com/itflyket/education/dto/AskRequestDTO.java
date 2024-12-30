package com.itflyket.education.dto;

public class AskRequestDTO {
    private Integer userId;
    private String question;

    private String username;


    public AskRequestDTO() {
    }

    public AskRequestDTO(Integer userId, String question, String username) {
        this.userId = userId;
        this.question = question;
        this.username = username;
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

    public String toString() {
        return "AskRequestDTO{userId = " + userId + ", question = " + question + ", username = " + username + "}";
    }
}
