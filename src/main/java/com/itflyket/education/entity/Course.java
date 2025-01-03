package com.itflyket.education.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

@TableName("courses")  // 指定数据库中的表名为 courses
public class Course {
    private Integer id;
    private String title;
    private String description;
    @TableField("teacherId")
    private Integer teacherId;
    @TableField("createTime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    private Date createTime;
    @TableField("updateTime")
    private Date updateTime;
    private String rating;
    private String category;
    private String img;
    private double price;
    @TableField("joinCount")
    private int joinCount;
    @TableField("courseStartTime")
    private String courseStartTime;
    private String detail;
    @TableField("learningGoal")
    private String learningGoal;

    @TableField(exist = false)
    private Teacher teacher;


    public Course() {
    }

    public Course(Integer id, String title, String description, Integer teacherId, Date createTime, Date updateTime, String rating, String category, String img, double price, int joinCount, String courseStartTime, String detail, String learningGoal, Teacher teacher) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.teacherId = teacherId;
        this.createTime = createTime;
        this.updateTime = updateTime;
        this.rating = rating;
        this.category = category;
        this.img = img;
        this.price = price;
        this.joinCount = joinCount;
        this.courseStartTime = courseStartTime;
        this.detail = detail;
        this.learningGoal = learningGoal;
        this.teacher = teacher;
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
     * @return title
     */
    public String getTitle() {
        return title;
    }

    /**
     * 设置
     * @param title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * 获取
     * @return description
     */
    public String getDescription() {
        return description;
    }

    /**
     * 设置
     * @param description
     */
    public void setDescription(String description) {
        this.description = description;
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
     * @return createTime
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * 设置
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * 获取
     * @return updateTime
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * 设置
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    /**
     * 获取
     * @return rating
     */
    public String getRating() {
        return rating;
    }

    /**
     * 设置
     * @param rating
     */
    public void setRating(String rating) {
        this.rating = rating;
    }

    /**
     * 获取
     * @return category
     */
    public String getCategory() {
        return category;
    }

    /**
     * 设置
     * @param category
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * 获取
     * @return img
     */
    public String getImg() {
        return img;
    }

    /**
     * 设置
     * @param img
     */
    public void setImg(String img) {
        this.img = img;
    }

    /**
     * 获取
     * @return price
     */
    public double getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * 获取
     * @return joinCount
     */
    public int getJoinCount() {
        return joinCount;
    }

    /**
     * 设置
     * @param joinCount
     */
    public void setJoinCount(int joinCount) {
        this.joinCount = joinCount;
    }

    /**
     * 获取
     * @return courseStartTime
     */
    public String getCourseStartTime() {
        return courseStartTime;
    }

    /**
     * 设置
     * @param courseStartTime
     */
    public void setCourseStartTime(String courseStartTime) {
        this.courseStartTime = courseStartTime;
    }

    /**
     * 获取
     * @return detail
     */
    public String getDetail() {
        return detail;
    }

    /**
     * 设置
     * @param detail
     */
    public void setDetail(String detail) {
        this.detail = detail;
    }

    /**
     * 获取
     * @return learningGoal
     */
    public String getLearningGoal() {
        return learningGoal;
    }

    /**
     * 设置
     * @param learningGoal
     */
    public void setLearningGoal(String learningGoal) {
        this.learningGoal = learningGoal;
    }

    /**
     * 获取
     * @return teacher
     */
    public Teacher getTeacher() {
        return teacher;
    }

    /**
     * 设置
     * @param teacher
     */
    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public String toString() {
        return "Course{id = " + id + ", title = " + title + ", description = " + description + ", teacherId = " + teacherId + ", createTime = " + createTime + ", updateTime = " + updateTime + ", rating = " + rating + ", category = " + category + ", img = " + img + ", price = " + price + ", joinCount = " + joinCount + ", courseStartTime = " + courseStartTime + ", detail = " + detail + ", learningGoal = " + learningGoal + ", teacher = " + teacher + "}";
    }
}
