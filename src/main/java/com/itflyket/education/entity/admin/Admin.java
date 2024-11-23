package com.itflyket.education.entity.admin;

public class Admin {
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

    public Admin(String name, String password,String avatar) {
        this.name = name;
        this.password = password;
        this.avatar = avatar;
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
