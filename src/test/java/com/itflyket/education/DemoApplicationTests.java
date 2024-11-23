package com.itflyket.education;
import com.itflyket.education.controller.user.AddUserController;
import com.itflyket.education.controller.user.GetUserAllController;
import com.itflyket.education.entity.User;
import com.itflyket.education.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;


@SpringBootTest
class DemoApplicationTests {
    //单元测试
    @Autowired
    private GetUserAllController getUserAllController;

    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private AddUserController addUserController;

    @Autowired
    private UserMapper userMapper;


    @Test
    void contextLoads() {
        //查询所有用户,并加密测试
        List<User> list = userMapper.selectList(null);
        for (User user : list) {
            if (user.getPassword().length() < 60){
                String encodePassword = passwordEncoder.encode(user.getPassword());
                user.setPassword(encodePassword);
                userMapper.updateById(user);
                System.out.println("加密用户名密码：" + user.getPassword());
            }
        }
    }




}
