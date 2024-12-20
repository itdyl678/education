package com.itflyket.education.dto;

public class CourseAndTeacherInfoDTO {
    private Integer id; // 课程ID
    private String title; // 课程标题
    private String description; // 课程简介
    private String rating; // 评分
    private String category; // 分类
    private String img; // 图片
    private String courseStartTime;  //开课时间
    private int joinCount;  //加入人数
    private Double price; // 价格
    private String detail; // 详细信息

    // 教师信息
    private String instructorName; // 教师姓名
    private String instructorTitle; // 教师职称
    private String instructorDescription; // 教师简介
    private String instructorPhoto; // 教师照片


    public CourseAndTeacherInfoDTO() {
    }

    public CourseAndTeacherInfoDTO(Integer id, String title, String description, String rating, String category, String img, String courseStartTime, int joinCount, Double price, String detail, String instructorName, String instructorTitle, String instructorDescription, String instructorPhoto) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.category = category;
        this.img = img;
        this.courseStartTime = courseStartTime;
        this.joinCount = joinCount;
        this.price = price;
        this.detail = detail;
        this.instructorName = instructorName;
        this.instructorTitle = instructorTitle;
        this.instructorDescription = instructorDescription;
        this.instructorPhoto = instructorPhoto;
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
     * @return price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * 设置
     * @param price
     */
    public void setPrice(Double price) {
        this.price = price;
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
     * @return instructorName
     */
    public String getInstructorName() {
        return instructorName;
    }

    /**
     * 设置
     * @param instructorName
     */
    public void setInstructorName(String instructorName) {
        this.instructorName = instructorName;
    }

    /**
     * 获取
     * @return instructorTitle
     */
    public String getInstructorTitle() {
        return instructorTitle;
    }

    /**
     * 设置
     * @param instructorTitle
     */
    public void setInstructorTitle(String instructorTitle) {
        this.instructorTitle = instructorTitle;
    }

    /**
     * 获取
     * @return instructorDescription
     */
    public String getInstructorDescription() {
        return instructorDescription;
    }

    /**
     * 设置
     * @param instructorDescription
     */
    public void setInstructorDescription(String instructorDescription) {
        this.instructorDescription = instructorDescription;
    }

    /**
     * 获取
     * @return instructorPhoto
     */
    public String getInstructorPhoto() {
        return instructorPhoto;
    }

    /**
     * 设置
     * @param instructorPhoto
     */
    public void setInstructorPhoto(String instructorPhoto) {
        this.instructorPhoto = instructorPhoto;
    }

    public String toString() {
        return "CourseAndTeacherInfoDTO{id = " + id + ", title = " + title + ", description = " + description + ", rating = " + rating + ", category = " + category + ", img = " + img + ", courseStartTime = " + courseStartTime + ", joinCount = " + joinCount + ", price = " + price + ", detail = " + detail + ", instructorName = " + instructorName + ", instructorTitle = " + instructorTitle + ", instructorDescription = " + instructorDescription + ", instructorPhoto = " + instructorPhoto + "}";
    }
}
