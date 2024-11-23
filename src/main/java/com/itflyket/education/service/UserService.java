package com.itflyket.education.service;

import com.itflyket.education.dto.LoginResponse;

public interface UserService {
    /**
     * 用户登录
     * @param username 用户名
     * @param password 密码
     * @return 生成的 JWT 令牌
     * @throws Exception 登录失败抛出异常
     */
    LoginResponse login(String username, String password) throws Exception;
}
