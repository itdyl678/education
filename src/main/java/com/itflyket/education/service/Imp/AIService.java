package com.itflyket.education.service.Imp;

import com.itflyket.education.bigmodel.OptionalAPI;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    /**
     * askAi 用于接收一个问题，并将其传递给大模型接口（OptionalAPI），返回AI的回答。
     * @param question 用户提交的问题
     * @return 大模型的回答
     */
    public String askAi(String question) {
        try {
            // 这里调用大模型的逻辑
            // 1. 实例化改造后的 OptionalAPI 类
            // 2. 调用其方法得到 AI 回复

            OptionalAPI optionalAPI = new OptionalAPI();
            // 设置问题
            String answer = optionalAPI.askAi(question);

            return answer;
        } catch (Exception e) {
            e.printStackTrace();
            return "AI服务调用出现异常: " + e.getMessage();
        }
    }
}
