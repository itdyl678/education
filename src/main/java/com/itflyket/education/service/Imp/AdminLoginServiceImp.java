package com.itflyket.education.service.Imp;

import com.itflyket.education.entity.admin.Admin;
import com.itflyket.education.mapper.AdminLoginMapper;
import com.itflyket.education.service.AdminLoginService;
import com.itflyket.education.utils.JwtTokenUtil;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminLoginServiceImp implements AdminLoginService {

    @Autowired
    private AdminLoginMapper adminLoginMapper;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public Admin adminLogin(String name, String password) {
        Admin admin = this.adminLoginMapper.adminLogin(name, password);
        if (admin != null && passwordMatches(admin.getPassword(), password)) {
            return admin;
        }
        return null;
    }

    public Admin findAdminByName(String name) {
        return this.adminLoginMapper.findAdminByName(name);
    }

    public String generateToken(Admin admin) {
        return jwtTokenUtil.generateToken(admin.getName()); // 使用工具类生成令牌
    }

    private boolean passwordMatches(String storedPassword, String rawPassword) {
        return storedPassword.equals(rawPassword);
    }
}
