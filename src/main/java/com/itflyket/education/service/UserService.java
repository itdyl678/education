package com.itflyket.education.service;
import com.baomidou.mybatisplus.extension.service.IService;
import com.itflyket.education.dto.LoginResponse;
import com.itflyket.education.dto.UserDTO;
import com.itflyket.education.entity.User;


public interface UserService extends IService<User> {
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 生成的 JWT 令牌
     * @throws Exception 登录失败抛出异常
     */
    LoginResponse login(String username, String password) throws Exception;


    /**
     * 用户注册
     * @param username 用户名
     * @param phone 手机号
     * @param password 密码
     * @param verificationCode 验证码
     * @param captchaId 验证码 ID
     * @throws Exception 注册失败抛出异常
     */
    void register(String username, String phone, String password, String verificationCode, String captchaId) throws Exception;

    /**
     * 根据id查询对应的用户数据
     * @param id
     * @return
     */
    UserDTO getUserById(Long id);

}
