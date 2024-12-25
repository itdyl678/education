package com.itflyket.education.controller.ai;
import com.itflyket.education.dto.AskRequestDTO;
import com.itflyket.education.dto.SaveHistoryDTO;
import com.itflyket.education.entity.ChatHistory;
import com.itflyket.education.service.ChatHistoryService;
import com.itflyket.education.service.Imp.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AIController {
    @Autowired
    private AIService aiService;

    @Autowired
    private ChatHistoryService chatHistoryService;

    /**
     * 用户提问接口
     * @param request 前端传递的问题内容和用户ID
     * @return AI回答结果
     */
    @PostMapping("/ask-question")
    public ResponseEntity<Map<String, Object>> askQuestion(@RequestBody AskRequestDTO request) {
        Integer userId = request.getUserId();
        String question = request.getQuestion();
        String username = request.getUsername();
        System.out.println("输入的用户id和问题是：" +userId +", "+ question + ", " + username);

        if (question == null || question.trim().isEmpty()) {
            Map<String, Object> response = new HashMap<>();
            response.put("error", "问题不能为空");
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Map<String, Object> response = new HashMap<>();
        try {
            //调用AI服务获取回答
            String answer = aiService.askAi(question);
            //封装对象
            SaveHistoryDTO saveHistoryDTO = new SaveHistoryDTO();
            saveHistoryDTO.setUsername(username);
            saveHistoryDTO.setUserId(userId);
            saveHistoryDTO.setQuestion(question);
            saveHistoryDTO.setAnswer(answer);

            // 获取当前时间并格式化
            LocalDateTime now = LocalDateTime.now();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss");
            String formattedNow = now.format(formatter);


            // 将格式化后的字符串解析为 LocalDateTime 对象
            LocalDateTime formattedDateTime = LocalDateTime.parse(formattedNow);

            // 将 LocalDateTime 对象设置到 DTO
            saveHistoryDTO.setCreatedAt(formattedDateTime);

            System.out.println(saveHistoryDTO.getCreatedAt()+ "=================");

            //保存到缓存
            chatHistoryService.saveToRedis(saveHistoryDTO);

            //保存到mysql
            chatHistoryService.saveToMySQL(saveHistoryDTO);

            //将结果放入Map集合，并返回给客户端
            response.put("result", answer);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "请求失败"+ e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }

    /**
     * 获取用户历史记录
     * @param userId
     * @return
     */
    @GetMapping("/get-history/{userId}")
    public ResponseEntity<Map<String,Object>> getHistory(@PathVariable Integer userId){
        Map<String,Object> response = new HashMap<>();
        try {
            //从redis获取历史记录
            List<Object> redisHistory = chatHistoryService.getFromRedis(userId);

            if (redisHistory == null && redisHistory.isEmpty()){
                //如果redis没有记录，从mysql获取
                List<ChatHistory> fromMySQL = chatHistoryService.getFromMySQL(userId);
                response.put("history",fromMySQL);
            }else {
                response.put("history",redisHistory);
            }
            return ResponseEntity.ok(response);
        }catch (Exception e){
          response.put("error","获取历史记录失败" +e.getMessage());
          return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response); //报错500提示信息
        }
    }

}
