package com.itflyket.education.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itflyket.education.dto.SaveHistoryDTO;
import com.itflyket.education.entity.ChatHistory;
import com.itflyket.education.mapper.ChatHistoryMapper;
import com.itflyket.education.service.ChatHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Service
public class ChatHistoryServiceImp implements ChatHistoryService {
    private static final String REDIS_KEY_PREFIX = "chat_history:";

    @Autowired
    private RedisTemplate<String,Object> redisTemplate;

    @Autowired
    private ChatHistoryMapper chatHistoryMapper;


    /**
     * 保存历史记录到redis
     * @param saveHistory
     */
    @Override
    public void saveToRedis(SaveHistoryDTO saveHistory) {
       String redisKey = REDIS_KEY_PREFIX + saveHistory.getUserId();
        System.out.println("缓存信息--------" + saveHistory);

       redisTemplate.opsForList().rightPush(redisKey,saveHistory);
       redisTemplate.expire(redisKey,7, TimeUnit.DAYS); //设置7天过期
    }

    /**
     * 从redis中获取历史记录
     * @param userId
     * @return
     */
    @Override
    public List<Object> getFromRedis(Integer userId) {
        String redisKey = REDIS_KEY_PREFIX + userId;

        // 获取列表的最后 30 条记录（-30 到 -1）
        List<Object> latest30 = redisTemplate.opsForList().range(redisKey, -30, -1);

        System.out.println("latest30:---"+latest30);
        if (latest30 == null) {
            return Collections.emptyList();
        }

        // 如果要让“最新”排在第一，就进行反转
        Collections.reverse(latest30);

        return latest30;
    }

    /**
     * 从mysql中获取历史记录
     * @param userId
     * @return
     */
    @Override
    public List<ChatHistory> getFromMySQL(Integer userId) {
        return chatHistoryMapper.selectList(
                new QueryWrapper<ChatHistory>().eq("userId",userId).orderByDesc("createdAt")
        );
    }

    /**
     * 保存信息到mysql
     * @param saveHistoryDTO
     */
    @Override
    public void saveToMySQL(SaveHistoryDTO saveHistoryDTO) {
        //将SaveHistoryDTO对象转换为ChatHistory对象
        ChatHistory chatHistory = new ChatHistory();
        chatHistory.setUserId(saveHistoryDTO.getUserId());
        chatHistory.setUsername(saveHistoryDTO.getUsername());
        chatHistory.setAnswer(saveHistoryDTO.getAnswer());
        chatHistory.setQuestion(saveHistoryDTO.getQuestion());
        chatHistory.setCreatedAt(saveHistoryDTO.getCreatedAt());

        chatHistoryMapper.insert(chatHistory);
    }

}
