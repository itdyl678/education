package com.itflyket.education.service;

import com.itflyket.education.entity.User;

import java.text.ParseException;

public interface AddUserService {
    //增加用户
    void addUser(User user) throws ParseException;
}
