package com.itflyket.education.controller.admin;

import com.itflyket.education.entity.admin.Admin;
import com.itflyket.education.entity.admin.LoginResponse;
import com.itflyket.education.service.AdminLoginService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
public class AdminLoginController {
    @Autowired
    private AdminLoginService adminLoginService;

    @PostMapping("/login")
    @CrossOrigin
    public ResponseEntity<Object> adminLogin(@RequestBody Admin admin) {  //将前端传过来的值封装为一个对象
        String name = admin.getName();
        String password = admin.getPassword();
        Admin admin1 = this.adminLoginService.adminLogin(name, password);

        if (admin1 != null){
            String token = adminLoginService.generateToken(admin1);
            System.out.println(token);
            System.out.println("登录成功！");
            return ResponseEntity.ok(new LoginResponse(token, admin1.getName()));  //返回生成jwt
        }else {
            return ResponseEntity.status(401).body("用户名或者密码有错误！");
        }
    }
}
