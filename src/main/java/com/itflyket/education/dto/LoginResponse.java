package com.itflyket.education.dto;

/**
 * 登录成功后将后端的一些数据放回给前端页面
 */
public class LoginResponse {
    private Long id;
    private String token;
    private String name;
    private String avatar;


    public LoginResponse() {
    }

    public LoginResponse(Long id ,String token, String name, String avatar) {
        this.token = token;
        this.name = name;
        this.avatar = avatar;
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * 获取
     * @return token
     */
    public String getToken() {
        return token;
    }

    /**
     * 设置
     * @param token
     */
    public void setToken(String token) {
        this.token = token;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
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

    public String toString() {
        return "LoginResponse{id = "+id+",token = " + token + ", name = " + name + ", avatar = " + avatar + "}";
    }
}

