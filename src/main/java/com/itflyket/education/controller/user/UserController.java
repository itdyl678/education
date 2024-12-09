package com.itflyket.education.controller.user;

import com.itflyket.education.dto.LoginResponse;
import com.itflyket.education.dto.UserDTO;
import com.itflyket.education.dto.UserLoginRequestDTO;
import com.itflyket.education.result.ResponseResult;
import com.itflyket.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.HashMap;
import java.util.Map;
import java.util.function.BiConsumer;

/**
 * 用户登录和注册功能
 */
@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 用户登录
     * @param userLoginRequestDTO
     * @return
     */
    @PostMapping("/login")
    public ResponseResult login(@RequestBody UserLoginRequestDTO userLoginRequestDTO)  {
        try {
            //将前端接受过来的用户名和密码作为login方法的参数，用来生成对应的DTO对象(LoginResponse)
            LoginResponse response = userService.login(userLoginRequestDTO.getUsername(), userLoginRequestDTO.getPassword());
            return ResponseResult.success("登录成功")
                    .data("id",response.getId())
                    .data("token",response.getToken())
                    .data("name",response.getName())
                    .data("avatar",response.getAvatar());
        } catch (Exception e) {
            return ResponseResult.fail(e.getMessage());
        }
    }

    /**
     * 用户注册
     * @param request
     * @return
     */
    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@RequestBody Map<String, String> request) {
        String username = request.get("username");
        String phone = request.get("phone");
        String password = request.get("password");
        String verificationCode = request.get("verificationCode");
        String captchaId = request.get("captchaId");

        System.out.println("--------------------------");
//        for (Map.Entry<String, String> entry : request.entrySet()) {
//            String key = entry.getKey();
//            String value = entry.getValue();
//            System.out.println("Key:" + key +",Value:"+value);
//        }

//        request.forEach(new BiConsumer<String, String>() {
//            @Override
//            public void accept(String key, String value) {
//                System.out.println("Key:" + key +",Value:"+value);
//            }
//        });

//        for (String key : request.keySet()) {
//            String value = request.get(key);
//            System.out.println("Key:" + key +",Value:"+value);
//        }


        Map<String, Object> response = new HashMap<>();

        try {
            // 调用服务层的注册方法，将注册信息传递给服务层进行处理
            userService.register(username, phone, password, verificationCode, captchaId);

            response.put("success", true);
            response.put("message", "注册成功");
            return ResponseEntity.ok(response);

        } catch (Exception e) {
            // 捕获服务层抛出的异常，并返回错误信息
            response.put("success", false);
            response.put("message", e.getMessage());
            return ResponseEntity.badRequest().body(response);
        }
    }

    /**
     * 根据用户的id去查询对应的用户数据
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> getUserById(@PathVariable Long id){

        System.out.println("plplplplplpdddddd"+id);
        UserDTO userById = userService.getUserById(id);
        if (userById != null){
            return ResponseEntity.ok(userById);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
    }
}
