package com.itflyket.education.service.Imp;

import com.itflyket.education.dto.UserDTO;
import com.itflyket.education.entity.User;
import com.itflyket.education.mapper.UpdateUserMapper;
import com.itflyket.education.service.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
import java.time.LocalDateTime;

@Service
public class UpdateUserServiceImp implements UpdateUserService {

    @Autowired
    private UpdateUserMapper updateUserMapper;

    /**
     * 改变用户信息
     * @param user
     * @return
     */
    @Override
    public int updateUser(UserDTO user) {
       return this.updateUserMapper.updateUser(user);
    }

    /**
     * 改变用户状态
     * @param user
     * @return
     */
    @Override
    public int transForUser(User user) {
        return this.updateUserMapper.transForUser(user);
    }
}
