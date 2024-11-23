package com.itflyket.education.result;

import java.util.HashMap;
import java.util.Map;

/**
 * 统一返回结果
 */
public class ResponseResult {
    private Boolean success;
    private Integer code;
    private String message;
    private Map<String, Object> data = new HashMap<>();

    // 私有构造方法
    private ResponseResult() {}

    // 成功静态方法
    public static ResponseResult success(String message) {
        ResponseResult result = new ResponseResult();
        result.setSuccess(true);
        result.setCode(200);
        result.setMessage(message);
        return result;
    }

    // 失败静态方法
    public static ResponseResult fail(String message) {
        ResponseResult result = new ResponseResult();
        result.setSuccess(false);
        result.setCode(500);
        result.setMessage(message);
        return result;
    }

    // 链式编程方法
    public ResponseResult data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    // Getter 和 Setter
    public Boolean getSuccess() { return success; }
    public void setSuccess(Boolean success) { this.success = success; }
    public Integer getCode() { return code; }
    public void setCode(Integer code) { this.code = code; }
    public String getMessage() { return message; }
    public void setMessage(String message) { this.message = message; }
    public Map<String, Object> getData() { return data; }
    public void setData(Map<String, Object> data) { this.data = data; }
}
