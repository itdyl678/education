package com.itflyket.education.service.Imp;

<<<<<<< HEAD
import com.itflyket.education.dto.UserDTO;
=======
>>>>>>> 18415b2107057a21876fda6d6c1e3377026d1b61
import com.itflyket.education.entity.User;
import com.itflyket.education.mapper.UpdateUserMapper;
import com.itflyket.education.service.UpdateUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.awt.*;
<<<<<<< HEAD
import java.time.LocalDateTime;
=======
>>>>>>> 18415b2107057a21876fda6d6c1e3377026d1b61

@Service
public class UpdateUserServiceImp implements UpdateUserService {

    @Autowired
    private UpdateUserMapper updateUserMapper;
<<<<<<< HEAD

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
=======
    @Override
    public int updateUser(User user) {

       return this.updateUserMapper.updateUser(user);
    }

>>>>>>> 18415b2107057a21876fda6d6c1e3377026d1b61
    @Override
    public int transForUser(User user) {
        return this.updateUserMapper.transForUser(user);
    }
}
