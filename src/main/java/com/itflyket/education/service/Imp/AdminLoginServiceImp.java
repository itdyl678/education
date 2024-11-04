package com.itflyket.education.service.Imp;

import com.itflyket.education.entity.admin.Admin;
import com.itflyket.education.mapper.AdminLoginMapper;
import com.itflyket.education.service.AdminLoginService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class AdminLoginServiceImp implements AdminLoginService {

    @Autowired
    private AdminLoginMapper adminLoginMapper;

    // 生成 JWT 时使用的密钥, 建议定期更换以保证安全性
    private final String SECRET_KEY = "bXlfbmV3X3NlY3JldF9rZXlfZm9yX2p3dF9wcm9qZWN0";
    private final long EXPIRATION_TIME = 86400000; // 过期时间： 1天，设置 JWT 的过期时间

    /**
     * 管理员登录逻辑，检查用户名和密码
     * @param name 管理员用户名
     * @param password 管理员密码
     * @return 返回 Admin 对象或 null
     */
    public Admin adminLogin(String name, String password) {
        Admin admin = this.adminLoginMapper.adminLogin(name, password);
        if (admin != null && passwordMatches(admin.getPassword(), password)) {
            return admin;  // 验证成功后返回用户
        }
        return null; // 用户名或密码不匹配
    }

    /**
     * 根据用户名查找管理员
     * @param name 用户名
     * @return Admin 对象
     */
    public Admin findAdminByName(String name) {
        return this.adminLoginMapper.findAdminByName(name); // 从数据库查找用户
    }

    /**
     * 生成 JWT 令牌
     * @param admin 管理员对象
     * @return JWT 令牌字符串
     */
    public String generateToken(Admin admin) {
        return Jwts.builder()
                .setSubject(admin.getName())  // 设置 JWT 的主题为用户名
                .setIssuedAt(new Date())  // 设置 JWT 的签发时间为当前时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // 设置 JWT 的过期时间为当前时间加上定义的过期时间
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)  // 使用 HS512 算法和密钥对 JWT 进行签名
                .compact();  // 生成最终的 JWT 字符串
    }

    /**
     * 验证密码是否匹配
     * @param storedPassword 数据库中存储的密码
     * @param rawPassword 用户输入的密码
     * @return true 如果密码匹配
     */
    private boolean passwordMatches(String storedPassword, String rawPassword) {
        return storedPassword.equals(rawPassword);
    }
}
