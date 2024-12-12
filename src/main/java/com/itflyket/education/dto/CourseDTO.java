package com.itflyket.education.dto;

public class CourseDTO {
    private Integer id;
    private String title;
    private String description;
    private String rating;
    private String category;
    private String img;
    private double price;
    private String detail;


    public CourseDTO() {
    }

    public CourseDTO(Integer id,String title, String description, String rating, String category, String img, double price, String detail) {
        this.title = title;
        this.description = description;
        this.rating = rating;
        this.category = category;
        this.img = img;
        this.price = price;
        this.detail = detail;
    }

    /**
     * 获取
     * @return title
     */
    public String getTitle() {
        return title;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public String toString() {
        return "CourseDTO{id = "+id+" +title = " + title + ", description = " + description + ", rating = " + rating + ", category = " + category + ", img = " + img + ", price = " + price + ", detail = " + detail + "}";
    }
}
