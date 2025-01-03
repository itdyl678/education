package com.itflyket.education.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itflyket.education.entity.User;
import com.itflyket.education.mapper.GetUserAllMapper;

import com.itflyket.education.service.Imp.GetUserAllServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class GetUserAllController {

    @Autowired
    private GetUserAllServiceImp getUserAllServiceImp;

    @GetMapping("/getUserAll")
    public IPage<User> getUserAll(@RequestParam(defaultValue = "1") Integer currentPage, // 当前页码，默认为1
                                  @RequestParam(defaultValue = "5") Integer pageSize, // 每页大小，默认为10
                                  @RequestParam(required = false) String search           // 搜索关键字，可选
    ) {
        System.out.println("当前页码数："+currentPage + "每页的大小：" + pageSize + "dddd" + search);
        return  getUserAllServiceImp.getUserPage(currentPage, pageSize,search);

    }
}
