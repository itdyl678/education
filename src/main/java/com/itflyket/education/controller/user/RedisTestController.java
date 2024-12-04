package com.itflyket.education.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.DataType;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

/**
 * 测试查询redis里面的数据
 */
@RestController
public class RedisTestController {

    @Autowired
    private StringRedisTemplate redisTemplate;

    @GetMapping("/test-redis")
    public String getAllRedisData() {
        try {
            Set<String> keys = redisTemplate.keys("*");
            if (keys == null || keys.isEmpty()) {
                return "Redis 数据库中没有任何数据";
            }

            StringBuilder result = new StringBuilder("Redis 数据库中的数据：\n");

            for (String key : keys) {
                DataType type = redisTemplate.type(key); // 获取键类型
                result.append("Key: ").append(key).append(", Type: ").append(type).append(", Value: ");

                switch (type) {
                    case STRING:
                        result.append(redisTemplate.opsForValue().get(key));
                        break;
                    case HASH:
                        result.append(redisTemplate.opsForHash().entries(key));
                        break;
                    case LIST:
                        result.append(redisTemplate.opsForList().range(key, 0, -1));
                        break;
                    case SET:
                        result.append(redisTemplate.opsForSet().members(key));
                        break;
                    case ZSET:
                        result.append(redisTemplate.opsForZSet().range(key, 0, -1));
                        break;
                    default:
                        result.append("不支持的类型");
                }
                result.append("<br>");  //前端换行效果
            }

            return result.toString();
        } catch (Exception e) {
            return "Redis 连接失败，错误信息：" + e.getMessage();
        }
    }
}
