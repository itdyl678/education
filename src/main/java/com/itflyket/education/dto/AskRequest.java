package com.itflyket.education.dto;

public class AskRequest {
    private String text;       // 用户输入的问题
    private String service;    // 用户选择的AI服务类型

    // 构造方法
    public AskRequest() {
    }

    public AskRequest(String text, String service) {
        this.text = text;
        this.service = service;
    }

    // Getter 和 Setter
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }
}
