package com.itflyket.education.mapper;

import com.itflyket.education.dto.UserDTO;

import com.itflyket.education.entity.User;

public interface UpdateUserMapper {
    //修改用户信息

    int updateUser(UserDTO user);


    //改变状态
    int transForUser(User user);
}
