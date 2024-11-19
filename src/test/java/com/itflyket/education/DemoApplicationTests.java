package com.itflyket.education;

<<<<<<< HEAD
import com.itflyket.education.controller.user.AddUserController;
=======
>>>>>>> 18415b2107057a21876fda6d6c1e3377026d1b61
import com.itflyket.education.controller.user.GetUserAllController;
import com.itflyket.education.entity.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
<<<<<<< HEAD
import org.springframework.web.bind.annotation.RequestBody;

import java.text.ParseException;
=======

>>>>>>> 18415b2107057a21876fda6d6c1e3377026d1b61
import java.util.List;

@SpringBootTest
class DemoApplicationTests {
    //单元测试
    @Autowired
    private GetUserAllController getUserAllController;

<<<<<<< HEAD
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
=======
    @Test
    void contextLoads() {
        List<User> list = getUserAllController.getUserAll();
        System.out.println(list);
    }
>>>>>>> 18415b2107057a21876fda6d6c1e3377026d1b61

}
