package com.itflyket.education.controller.ai;

import com.itflyket.education.dto.AskRequest;
import com.itflyket.education.service.Imp.AIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class AIController {
    @Autowired
    private AIService aiService;

    @PostMapping("/ask-question")
    public ResponseEntity<Map<String, Object>> askQuestion(@RequestBody AskRequest request) {
        Map<String, Object> response = new HashMap<>();
        try {
            String result = aiService.getAnswerFromXunfei(request.getText(), request.getService());
            response.put("result", result);
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("error", "请求失败");
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
        }
    }
}
