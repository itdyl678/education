package com.itflyket.education.service.Imp;

import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

@Service
public class AIService {
    private static final String XUNFEI_API_URL = "";
    private static final String API_KEY= "";

    public String getAnswerFromXunfei(String text,String service){
        //调用讯飞的API的接口
        //使用HttpClient发送请求，构造请求体，调用API,获取返回的答案
        //根据API文档格式传递参数
        String response = callXunFeiApi(text,service);
        return parseXunfeiResponse(response);
    }

    private String callXunFeiApi(String text,String service){
        //这里调用讯飞Api的请求逻辑
        //使用HttpClient发送post请求并获取结果
        return "";
    }
    private String parseXunfeiResponse(String response){
        return response;
    }
}
