package com.itflyket.education.service.Imp;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itflyket.education.dto.LoginResponse;
import com.itflyket.education.entity.User;
import com.itflyket.education.mapper.UserMapper;
import com.itflyket.education.service.UserService;
import com.itflyket.education.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    PasswordEncoder passwordEncoder; // 用于密码加密和校验
    @Autowired
    private JwtTokenUtil jwtTokenUtil; // 您的 JWT 工具类
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

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
        return new LoginResponse(user.getId(),token, user.getUsername(), user.getAvatar());
    }

    @Override
    public void register(String username, String phone, String password, String verificationCode, String captchaId) throws Exception {
        // 验证验证码
        String storedAnswer = redisTemplate.opsForValue().get("CAPTCHA_" + captchaId);
        if (storedAnswer == null || !storedAnswer.equals(verificationCode)) {
            throw new Exception("验证码错误或已过期");
        }

        // 检查用户名是否已存在
        QueryWrapper<User> userWrapperByName = new QueryWrapper<>();
        userWrapperByName.eq("username", username);
        if (userMapper.selectCount(userWrapperByName) > 0) {
            throw new Exception("用户名已存在");
        }

        // 检查手机号是否已注册
        QueryWrapper<User> userWrapperByPhone = new QueryWrapper<>();
        userWrapperByPhone.eq("phone", phone);
        if (userMapper.selectCount(userWrapperByPhone) > 0) {
            throw new Exception("手机号已注册");
        }

        // 保存用户信息
        User user = new User();
        user.setUsername(username);
        user.setPhone(phone);
        user.setPassword(passwordEncoder.encode(password)); // 对密码进行加密处理

        userMapper.insert(user);

        // 删除验证码
        redisTemplate.delete("CAPTCHA_" + captchaId);
    }


}
