package com.itflyket.education.service.Imp;

import com.itflyket.education.entity.User;
import com.itflyket.education.mapper.AddUserMapper;
import com.itflyket.education.service.AddUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class AddUserServiceImp implements AddUserService {

    @Autowired
    private AddUserMapper addUserMapper;

    @Autowired
    private PasswordEncoder passwordEncoder;  //密码加密

    /**
     * 增加用户信息
     *
     * @param user
     */
    @Override
    public void addUser(User user) {
        user.setStatus("1");
        user.setAvatar("url");

        if (user.getPassword().length() < 60){
            System.out.println("加密前的密码为" + user.getPassword());
            String encodePassword = passwordEncoder.encode(user.getPassword());
            user.setPassword(encodePassword);
            System.out.println("加密后的用户名密码：" + user.getPassword());
        }

        // 获取当前前端传来的时间
        Date originalCreatedAt = user.getCreatedAt();
        Date originalUpdatedAt = user.getUpdatedAt();

        // 调整为东八区时间
        Calendar calendar = Calendar.getInstance();

        // 调整 createdAt
        if (originalCreatedAt != null) {
            calendar.setTime(originalCreatedAt);
            calendar.add(Calendar.HOUR, -8); // 调整为东八区时间
            Date adjustedCreatedAt = calendar.getTime();
            user.setCreatedAt(adjustedCreatedAt);
        } else {
            user.setCreatedAt(new Date()); // 如果 createdAt 为空，设置为当前时间
        }

        // 调整 updatedAt
        if (originalUpdatedAt != null) {
            calendar.setTime(originalUpdatedAt);
            calendar.add(Calendar.HOUR, -8); // 调整为东八区时间
            Date adjustedUpdatedAt = calendar.getTime();
            user.setUpdatedAt(adjustedUpdatedAt);
        } else {
            user.setUpdatedAt(new Date()); // 如果 updatedAt 为空，设置为当前时间
        }

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
