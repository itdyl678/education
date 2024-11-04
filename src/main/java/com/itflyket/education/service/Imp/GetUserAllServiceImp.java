package com.itflyket.education.service.Imp;

import com.itflyket.education.entity.User;
import com.itflyket.education.mapper.GetUserAllMapper;
import com.itflyket.education.service.GetUserAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class GetUserAllServiceImp implements GetUserAllService {

    @Autowired
    private GetUserAllMapper getUserAllMapper;
    @Override
    public List<User> getUserAll() {
        List<User> users = this.getUserAllMapper.getUserAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        for (User user : users) {
            if (user.getCreatedAt() != null) {
                user.setFormattedCreatedAt(sdf.format(user.getCreatedAt()));
            }
            if (user.getUpdatedAt() != null) {
                user.setFormattedUpdatedAt(sdf.format(user.getUpdatedAt()));
            }
        }

        return users;
        }
}
