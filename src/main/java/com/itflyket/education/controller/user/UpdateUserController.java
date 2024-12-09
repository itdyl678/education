package com.itflyket.education.controller.user;
import com.itflyket.education.dto.LoginResponse;
import com.itflyket.education.dto.UserDTO;
import com.itflyket.education.entity.User;
import com.itflyket.education.service.GetUserAllService;
import com.itflyket.education.service.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import java.time.LocalDateTime;
import java.util.List;


@RestController
@RequestMapping("/users")
public class UpdateUserController {

    @Autowired
    private UpdateUserService updateUserService;

    @Autowired
    private GetUserAllService getUserAllService;

    @PutMapping("/updateUser/{id}")

    public ResponseEntity<String> updateUser(@PathVariable Long id, @RequestBody UserDTO user){

        System.out.println("看看前端传过来的数据"+user);
        // 将路径中的 id 设置到 user 对象中
        user.setId(id);
        //将本地时间放入user中进行存储
        user.setUpdatedAt(LocalDateTime.now());


        int result = this.updateUserService.updateUser(user);

        if (result > 0) {
            //更新后再调用接口从数据库中查询该用户的头像信息并返回给前端用来更新头像
            List<User> users = getUserAllService.getUserAll();
            System.out.println("查看更新后的用户信息："+users);
            String avatar = users.get(Math.toIntExact(id) - 1).getAvatar(); //数据库中的索引是从0开始，索引查询时要-1
            return ResponseEntity.ok(avatar);  // 返回成功的头像地址

        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("用户未找到");  // 返回失败消息
        }
    }

    /**
     * 用户状态的更新
     * @param id
     * @param user
     * @return
     */
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
