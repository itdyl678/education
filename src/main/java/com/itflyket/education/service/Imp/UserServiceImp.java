package com.itflyket.education.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itflyket.education.dto.LoginResponse;
import com.itflyket.education.entity.User;
import com.itflyket.education.mapper.UserMapper;
import com.itflyket.education.service.UserService;
import com.itflyket.education.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImp implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder; // 用于密码加密和校验
    @Autowired
    private JwtTokenUtil jwtTokenUtil; // 您的 JWT 工具类

    @Override
    public LoginResponse login(String username, String password) throws Exception {
        // 根据用户名查询用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);

        if (user == null) {
            throw new Exception("用户不存在");
        }

        // 校验密码
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new Exception("密码错误");
        }

        // 生成 JWT 令牌
        String token = jwtTokenUtil.generateToken(username);

        // 返回封装了所有信息的 DTO
        return new LoginResponse(token, user.getUsername(), user.getAvatar());
    }

}
