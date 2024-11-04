package com.itflyket.education.controller.user;

import com.itflyket.education.entity.User;
import com.itflyket.education.service.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@RestController
@RequestMapping("/users")
public class AddUserController {

    @Autowired
    private AddUserService addUserService;
    @PostMapping("/addUser")
    @CrossOrigin
    public ResponseEntity<String> addUser(@RequestBody User user) throws ParseException {
        System.out.println("-----========================");
        System.out.println(user);
        this.addUserService.addUser(user);
        return ResponseEntity.status(HttpStatus.CREATED).body("用户添加成功");  //返回数据给前端的开发者工具的响应
    }
}
