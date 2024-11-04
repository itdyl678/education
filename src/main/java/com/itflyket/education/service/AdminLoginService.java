package com.itflyket.education.service;

import com.itflyket.education.entity.admin.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminLoginService {
    //管理员登录
    Admin adminLogin(@Param("name") String name, @Param("password") String password);

    Admin findAdminByName(@Param("name") String name);
    //生成jwt令牌
    public String generateToken(Admin admin);
}
