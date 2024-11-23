package com.itflyket.education.controller.user;

import com.itflyket.education.dto.LoginResponse;
import com.itflyket.education.dto.UserLoginRequestDTO;
import com.itflyket.education.result.ResponseResult;
import com.itflyket.education.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/login")
    public ResponseResult login(@RequestBody UserLoginRequestDTO userLoginRequestDTO)  {
        try {
            //将前端接受过来的用户名和密码作为login方法的参数，用来生成对应的DTO对象(LoginResponse)
            LoginResponse response = userService.login(userLoginRequestDTO.getUsername(), userLoginRequestDTO.getPassword());
            return ResponseResult.success("登录成功")
                    .data("token",response.getToken())
                    .data("name",response.getName())
                    .data("avatar",response.getAvatar());
        } catch (Exception e) {
            return ResponseResult.fail(e.getMessage());
        }

    }
}
