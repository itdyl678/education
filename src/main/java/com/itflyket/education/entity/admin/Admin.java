package com.itflyket.education.entity.admin;

import com.itflyket.education.dto.LoginResponse;

public class Admin {
    private Long id;
    private String name;
    private String password;
    private String avatar;

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Admin() {
    }

    public Admin(Long id,String name, String password, String avatar) {
        this.name = name;
        this.password = password;
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
     * @return password
     */
    public String getPassword() {
        return password;
    }

    /**
     * 设置
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    public String toString() {
        return "Admin{name = " + name + ", password = " + password + "}";
    }
}
