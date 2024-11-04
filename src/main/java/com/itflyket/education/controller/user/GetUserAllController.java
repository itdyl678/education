package com.itflyket.education.controller.user;

import com.itflyket.education.entity.User;
import com.itflyket.education.service.GetUserAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
public class GetUserAllController {

    @Autowired
    private GetUserAllService getUserAllService;

    @GetMapping("/getUserAll")
    @CrossOrigin
    public List<User> getUserAll(){
        List<User> userAll = this.getUserAllService.getUserAll();
        System.out.println("--------------------------------------");
        System.out.println(userAll);
        return userAll;
    }
}
