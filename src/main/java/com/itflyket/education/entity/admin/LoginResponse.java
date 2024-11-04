package com.itflyket.education.entity.admin;

public class LoginResponse {
    private String token;
    private String name;


    public LoginResponse() {
    }

    public LoginResponse(String token, String name) {
        this.token = token;
        this.name = name;
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

    public String toString() {
        return "LoginResponse{token = " + token + ", name = " + name + "}";
    }
}

