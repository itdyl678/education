package com.itflyket.education.service.Imp;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.itflyket.education.dto.LoginResponse;
import com.itflyket.education.dto.UserDTO;
import com.itflyket.education.entity.User;
import com.itflyket.education.mapper.UserMapper;
import com.itflyket.education.service.UserService;
import com.itflyket.education.utils.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;


@Service
public class UserServiceImp extends ServiceImpl<UserMapper, User> implements UserService {
    @Autowired
    private UserMapper userMapper;   //Mybatis-plus的Mapper
    @Autowired
    PasswordEncoder passwordEncoder; // 用于密码加密和校验
    @Autowired
    private JwtTokenUtil jwtTokenUtil; // 您的 JWT 工具类
    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return
     * @throws Exception
     */
    @Override
    public LoginResponse login(String username, String password) throws Exception {
        // 根据用户名查询用户
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("username", username);
        User user = userMapper.selectOne(queryWrapper);


        if (user == null) {
            throw new Exception("用户不存在");
        }

        // 检查用户状态
        if (user.getStatus() != null && "0".equals(user.getStatus())) {
            throw new Exception("用户状态被锁定，无法登录");
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

    /**
     * 用户注册
     * @param username 用户名
     * @param phone 手机号
     * @param password 密码
     * @param verificationCode 验证码
     * @param captchaId 验证码 ID
     * @throws Exception
     */
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
        user.setStatus("1");  //默认用户状态正常可用
        user.setCreatedAt(new Date()); //设置初次注册的时间
        user.setUsername(username);
        user.setPhone(phone);
        user.setPassword(passwordEncoder.encode(password)); // 对密码进行加密处理

        userMapper.insert(user);

        // 删除验证码
        redisTemplate.delete("CAPTCHA_" + captchaId);
    }

    /**
     * 更具用户的id去查询对应的用户数据
     * @param id
     * @return
     */
    @Override
    public UserDTO getUserById(Long id) {
        User user = userMapper.selectById(id);
        if (user != null){
            UserDTO userDTO = new UserDTO(); //将实体类转换为DTO(数据传输对象)，避免暴露隐私信息
            userDTO.setUsername(user.getUsername());
            userDTO.setAge(user.getAge());
            userDTO.setGender(user.getGender());
            userDTO.setIdCard(user.getIdCard());
            userDTO.setAvatar(user.getAvatar());
            userDTO.setPhone(user.getPhone());
            userDTO.setEmail(user.getEmail());
            return userDTO;
        }
        return null;
    }


}
