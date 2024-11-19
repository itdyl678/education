package com.itflyket.education.controller.user;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itflyket.education.entity.User;
import com.itflyket.education.mapper.GetUserAllMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/users")
public class GetUserAllController {

    @Autowired

    private GetUserAllMapper getUserAllMapper;

    @GetMapping("/getUserAll")
    public IPage<User> getUserAll(@RequestParam(defaultValue = "1") Integer currentPage, // 当前页码，默认为1
                                  @RequestParam(defaultValue = "10") Integer pageSize, // 每页大小，默认为10
                                  @RequestParam(required = false) String search           // 搜索关键字，可选
    ) {
        System.out.println(currentPage + "[[[[[[[[[[" + pageSize + "dddd" + search);
        Page<User> page = new Page<>(currentPage, pageSize);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(); // 创建查询条件包装器（如需要可以添加条件）

        // 如果有搜索条件，则根据用户名进行模糊查询
        if (search != null && !search.trim().isEmpty()) {
            queryWrapper.lambda().like(User::getUsername, search);
        }

        return getUserAllMapper.selectPage(page, queryWrapper); // 直接调用 Mapper 层的分页方法


    }
}
