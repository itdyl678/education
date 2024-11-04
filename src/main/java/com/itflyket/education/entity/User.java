package com.itflyket.education.entity;


import java.util.Date;

public class User {

    private Long id;
    private String username;
    private String password;
    private int age;
    private String gender;
    private String email;
    private String phone;
    private String idCard;
    private Date createdAt; // 使用 Date 类型
    private String status;
    private String avatar;
    private Date updatedAt; // 使用 Date 类型


    // 新增的字段
    private String formattedCreatedAt;
    private String formattedUpdatedAt;


    public User() {
    }

    public User(Long id, String username, String password, int age, String gender, String email, String phone, String idCard, Date createdAt, String status, String avatar, Date updatedAt, String formattedCreatedAt, String formattedUpdatedAt) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.age = age;
        this.gender = gender;
        this.email = email;
        this.phone = phone;
        this.idCard = idCard;
        this.createdAt = createdAt;
        this.status = status;
        this.avatar = avatar;
        this.updatedAt = updatedAt;
        this.formattedCreatedAt = formattedCreatedAt;
        this.formattedUpdatedAt = formattedUpdatedAt;
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

    /**
     * 获取
     * @return age
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * 获取
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取
     * @return email
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置
     * @param email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * 获取
     * @return phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置
     * @param phone
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * 获取
     * @return idCard
     */
    public String getIdCard() {
        return idCard;
    }

    /**
     * 设置
     * @param idCard
     */
    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * 获取
     * @return createdAt
     */
    public Date getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置
     * @param createdAt
     */
    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取
     * @return status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 设置
     * @param status
     */
    public void setStatus(String status) {
        this.status = status;
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
     * @return formattedCreatedAt
     */
    public String getFormattedCreatedAt() {
        return formattedCreatedAt;
    }

    /**
     * 设置
     * @param formattedCreatedAt
     */
    public void setFormattedCreatedAt(String formattedCreatedAt) {
        this.formattedCreatedAt = formattedCreatedAt;
    }

    /**
     * 获取
     * @return formattedUpdatedAt
     */
    public String getFormattedUpdatedAt() {
        return formattedUpdatedAt;
    }

    /**
     * 设置
     * @param formattedUpdatedAt
     */
    public void setFormattedUpdatedAt(String formattedUpdatedAt) {
        this.formattedUpdatedAt = formattedUpdatedAt;
    }

    public String toString() {
        return "User{id = " + id + ", username = " + username + ", password = " + password + ", age = " + age + ", gender = " + gender + ", email = " + email + ", phone = " + phone + ", idCard = " + idCard + ", createdAt = " + (formattedCreatedAt != null ? formattedCreatedAt : createdAt) + ", status = " + status + ", avatar = " + avatar + ", updatedAt = " + (formattedUpdatedAt != null ? formattedUpdatedAt : updatedAt) + "}";
    }
}
