package com.itflyket.education.service.Imp;

import com.itflyket.education.entity.User;
import com.itflyket.education.mapper.AddUserMapper;
import com.itflyket.education.service.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class AddUserServiceImp implements AddUserService {

    @Autowired
    private AddUserMapper addUserMapper;

    /**
     * 增加用户信息
     *
     * @param user
     */
    @Override
    public void addUser(User user) {
        user.setStatus("1");
        user.setAvatar("url");

        // 获取当前前端传来的时间
        Date originalCreatedAt = user.getCreatedAt();
        Date originalUpdatedAt = user.getUpdatedAt();

        // 调整为东八区时间
        Calendar calendar = Calendar.getInstance();

        // 调整 createdAt
        calendar.setTime(originalCreatedAt);
        calendar.add(Calendar.HOUR, -8); // 调整为东八区时间
        Date adjustedCreatedAt = calendar.getTime();
        user.setCreatedAt(adjustedCreatedAt);

        // 调整 updatedAt
        calendar.setTime(originalUpdatedAt);
        calendar.add(Calendar.HOUR, -8); // 调整为东八区时间
        Date adjustedUpdatedAt = calendar.getTime();
        user.setUpdatedAt(adjustedUpdatedAt);

        // 将用户信息存入数据库
        this.addUserMapper.addUser(user);
    }
}
