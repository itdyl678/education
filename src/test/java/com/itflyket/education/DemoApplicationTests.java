package com.itflyket.education;

import com.itflyket.education.controller.user.GetUserAllController;
import com.itflyket.education.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    //单元测试
    @Autowired
    private GetUserAllController getUserAllController;

    @Test
    void contextLoads() {
        List<User> list = getUserAllController.getUserAll();
        System.out.println(list);
    }

}
