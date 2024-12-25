package com.itflyket.education.service;

import com.itflyket.education.dto.SaveHistoryDTO;
import com.itflyket.education.entity.ChatHistory;

import java.util.List;

public interface ChatHistoryService {
    //保存记录到redis
    void saveToRedis(SaveHistoryDTO saveHistory);

    //从redis中获取历史记录
    List<Object> getFromRedis(Integer userId);

    //从mysql中获取历史记录
    List<ChatHistory> getFromMySQL(Integer userId);
//
//    //同步 Redis 到 MySQL
//    void syncRedisToMySQL(Integer userId);

    //将记录保存到Mysql数据库
    void saveToMySQL( SaveHistoryDTO saveHistoryDTO);
}
