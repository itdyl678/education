package com.itflyket.education.dto;


public class UserCommentDTO {
    private Long id;
    private String username;
    private String avatar;


    public UserCommentDTO() {
    }

    public UserCommentDTO(Long id, String username, String avatar) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;

    }

    /**
     * 获取
     * @return id
     */
    public Long getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(Long id) {
        this.id = id;
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
     * @return avatar
     */
    public String getAvatar() {
        return avatar;
    }

    /**
     * 设置
     * @param avatar
     */
    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    /**
     * 获取
     * @return createAt
     */

    public String toString() {
        return "UserCommentDTO{id = " + id + ", username = " + username + ", avatar = " + avatar + ",}";
    }
}
