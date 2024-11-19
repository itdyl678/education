package com.itflyket.education.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import com.itflyket.education.entity.User;

import java.util.List;


public interface GetUserAllMapper extends BaseMapper<User> {

    //获取用户所有数据
    List<User> getUserAll();
}
