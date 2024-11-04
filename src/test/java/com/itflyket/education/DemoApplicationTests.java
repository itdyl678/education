package com.itflyket.education;

import com.itflyket.education.controller.user.AddUserController;
import com.itflyket.education.controller.user.GetUserAllController;
import com.itflyket.education.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    //单元测试
    @Autowired
    private GetUserAllController getUserAllController;

    @Autowired
    private AddUserController addUserController;

//    @Test
//    void contextLoads() {
//        List<User> list = getUserAllController.getUserAll();
//        System.out.println(list);
//    }

//    @Test
//    void addUser() throws ParseException {
//      User user = new User();
//      user.setUsername("戴玉龙");
//      user.setPassword("242526");
//
//      addUserController.addUser(user);
//    }

}
