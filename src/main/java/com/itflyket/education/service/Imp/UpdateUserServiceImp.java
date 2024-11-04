package com.itflyket.education.service.Imp;

import com.itflyket.education.entity.User;
import com.itflyket.education.mapper.UpdateUserMapper;
import com.itflyket.education.service.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;

@Service
public class UpdateUserServiceImp implements UpdateUserService {

    @Autowired
    private UpdateUserMapper updateUserMapper;
    @Override
    public int updateUser(User user) {

       return this.updateUserMapper.updateUser(user);
    }

    @Override
    public int transForUser(User user) {
        return this.updateUserMapper.transForUser(user);
    }
}
