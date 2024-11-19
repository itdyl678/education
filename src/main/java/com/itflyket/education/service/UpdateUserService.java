package com.itflyket.education.service;
import com.itflyket.education.dto.UserDTO;
import com.itflyket.education.entity.User;

public interface UpdateUserService {
    //修改用户信息

    int updateUser(UserDTO user);

    //改变状态
    int transForUser(User user);
}
