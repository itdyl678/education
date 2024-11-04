package com.itflyket.education.mapper;

import com.itflyket.education.entity.admin.Admin;
import org.apache.ibatis.annotations.Param;

public interface AdminLoginMapper {
     //管理员登录
    Admin adminLogin(@Param("name") String name,@Param("password") String password);

    Admin findAdminByName(@Param("name") String name);
    //生成jwt令牌
    public String generateToken(Admin admin);
}
