package com.itflyket.education.mapper;

<<<<<<< HEAD
import com.itflyket.education.dto.UserDTO;
=======
>>>>>>> 18415b2107057a21876fda6d6c1e3377026d1b61
import com.itflyket.education.entity.User;

public interface UpdateUserMapper {
    //修改用户信息
<<<<<<< HEAD
    int updateUser(UserDTO user);
=======
    int updateUser(User user);
>>>>>>> 18415b2107057a21876fda6d6c1e3377026d1b61

    //改变状态
    int transForUser(User user);
}
