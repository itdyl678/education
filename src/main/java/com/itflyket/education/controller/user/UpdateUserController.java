package com.itflyket.education.controller.user;

<<<<<<< HEAD
import com.itflyket.education.dto.UserDTO;
=======
>>>>>>> 18415b2107057a21876fda6d6c1e3377026d1b61
import com.itflyket.education.entity.User;
import com.itflyket.education.service.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

<<<<<<< HEAD
import java.time.LocalDateTime;

=======
>>>>>>> 18415b2107057a21876fda6d6c1e3377026d1b61
@RestController
@RequestMapping("/users")
public class UpdateUserController {

    @Autowired
    private UpdateUserService updateUserService;

    @PutMapping("/updateUser/{id}")
<<<<<<< HEAD
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDTO user){
        // 将路径中的 id 设置到 user 对象中
        user.setId(id);
        //将本地时间放入user中进行存储
        user.setUpdatedAt(LocalDateTime.now());
=======
    @CrossOrigin
    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody User user){
        // 将路径中的 id 设置到 user 对象中
        user.setId(id);
>>>>>>> 18415b2107057a21876fda6d6c1e3377026d1b61

        int result = this.updateUserService.updateUser(user);

        if (result > 0) {
            return ResponseEntity.ok("更新成功");  // 返回成功消息
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("用户未找到");  // 返回失败消息
        }
    }

    @PutMapping ("/transForUser/{id}")
    @CrossOrigin
    public ResponseEntity<String> transForUser(@PathVariable Long id,@RequestBody User user){
        System.out.println("*****************************************");
        // 将路径中的 id 设置到 user 对象中
        user.setId(id);

        // 转换状态值为数据库可识别的整数字符串 (0 表示禁用，1 表示正常)
        if ("正常".equals(user.getStatus())) {
            user.setStatus("1");  // 设置为 1 表示正常
        } else if ("禁用".equals(user.getStatus())) {
            user.setStatus("0");  // 设置为 0 表示禁用
        }

        // 调用 service 层进行更新操作
        int result = updateUserService.transForUser(user);

        // 如果更新成功，返回状态码 200 并提示成功
        if (result > 0) {
            return ResponseEntity.ok("更新成功");
        } else {
            // 如果用户不存在，返回状态码 404
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("用户未找到");
        }
    }
}
