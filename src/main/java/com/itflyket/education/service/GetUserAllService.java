package com.itflyket.education.service;

import com.itflyket.education.entity.User;

import java.util.List;

public interface GetUserAllService {
    //获取所有用户信息
    List<User> getUserAll();

    //查询所有的用户年龄
    List<Integer> getUserAge();
}
