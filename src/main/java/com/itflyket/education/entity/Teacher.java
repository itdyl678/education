package com.itflyket.education.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import java.util.Date;
import java.util.List;

@TableName("teachers")
public class Teacher {
    private Integer id;
    @TableField("teacherName")
    private String teacherName;
    private String title;
    private String subject;
    private String photo;
    private String achievements;
    private String detail;
    @TableField("createdTime")
    private Date createdTime;
    private String education;
    @TableField("teachingSubjects")
    private String teachingSubjects;

    @TableField(exist = false)
    private List<Comment> comments; //评论字段，表示当前老师的所有评论

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public Teacher() {
    }

    public Teacher(Integer id, String teacherName, String title, String subject, String photo, String achievements, String detail, Date createdTime, String education, String teachingSubjects) {
        this.id = id;
        this.teacherName = teacherName;
        this.title = title;
        this.subject = subject;
        this.photo = photo;
        this.achievements = achievements;
        this.detail = detail;
        this.createdTime = createdTime;
        this.education = education;
        this.teachingSubjects = teachingSubjects;
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
     * @return teacherName
     */
    public String getTeacherName() {
        return teacherName;
    }

    /**
     * 设置
     * @param teacherName
     */
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
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
     * @return subject
     */
    public String getSubject() {
        return subject;
    }

    /**
     * 设置
     * @param subject
     */
    public void setSubject(String subject) {
        this.subject = subject;
    }

    /**
     * 获取
     * @return photo
     */
    public String getPhoto() {
        return photo;
    }

    /**
     * 设置
     * @param photo
     */
    public void setPhoto(String photo) {
        this.photo = photo;
    }

    /**
     * 获取
     * @return achievements
     */
    public String getAchievements() {
        return achievements;
    }

    /**
     * 设置
     * @param achievements
     */
    public void setAchievements(String achievements) {
        this.achievements = achievements;
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
     * @return createTime
     */
    public Date getCreatedTime() {
        return createdTime;
    }

    /**
     * 设置
     * @param createdTime
     */
    public void setCreatedTime(Date createdTime) {
        this.createdTime = createdTime;
    }

    /**
     * 获取
     * @return education
     */
    public String getEducation() {
        return education;
    }

    /**
     * 设置
     * @param education
     */
    public void setEducation(String education) {
        this.education = education;
    }

    /**
     * 获取
     * @return teachingSubjects
     */
    public String getTeachingSubjects() {
        return teachingSubjects;
    }

    /**
     * 设置
     * @param teachingSubjects
     */
    public void setTeachingSubjects(String teachingSubjects) {
        this.teachingSubjects = teachingSubjects;
    }

    public String toString() {
        return "Teacher{id = " + id + ", teacherName = " + teacherName + ", title = " + title + ", subject = " + subject + ", photo = " + photo + ", achievements = " + achievements + ", detail = " + detail + ", createdTime = " + createdTime + ", education = " + education + ", teachingSubjects = " + teachingSubjects + "}";
    }
}
