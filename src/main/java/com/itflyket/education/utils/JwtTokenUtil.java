package com.itflyket.education.utils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenUtil {

    // 生成 JWT 时使用的密钥, 建议定期更换以保证安全性
    private final String SECRET_KEY = "bXlfbmV3X3NlY3JldF9rZXlfZm9yX2p3dF9wcm9qZWN0";
    private final long EXPIRATION_TIME = 86400000; // 过期时间：1天

    /**
     * 生成 JWT 令牌
     * @param subject 令牌的主体，一般是用户名
     * @return JWT 令牌字符串
     */
    public String generateToken(String subject) {
        return Jwts.builder()
                .setSubject(subject)  // 设置 JWT 的主体
                .setIssuedAt(new Date())  // 签发时间
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))  // 过期时间
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)  // 使用 HS512 算法签名
                .compact();
    }

    /**
     * 验证并解析 JWT 令牌，获取其中的声明信息
     * @param token JWT 令牌
     * @return 令牌中的声明信息
     */
    public Claims getClaimsFromToken(String token) {
        return Jwts.parser()
                .setSigningKey(SECRET_KEY)
                .parseClaimsJws(token)
                .getBody();
    }

    /**
     * 从 JWT 中获取用户名
     * @param token JWT 令牌
     * @return 用户名
     */
    public String getUsernameFromToken(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getSubject();
    }

    /**
     * 检查 JWT 是否过期
     * @param token JWT 令牌
     * @return true 如果令牌过期
     */
    public boolean isTokenExpired(String token) {
        Claims claims = getClaimsFromToken(token);
        return claims.getExpiration().before(new Date());
    }

    /**
     * 验证 JWT 令牌
     * @param token JWT 令牌
     * @param username 用户名
     * @return true 如果令牌有效且用户名匹配
     */
    public boolean validateToken(String token, String username) {
        String tokenUsername = getUsernameFromToken(token);
        return (username.equals(tokenUsername) && !isTokenExpired(token));
    }
}

