package com.itflyket.education.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.itflyket.education.entity.User;
import com.itflyket.education.entity.admin.Admin;
import com.itflyket.education.mapper.AdminLoginMapper;
import org.springframework.security.core.userdetails.UserDetails;
import com.itflyket.education.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static net.sf.jsqlparser.util.validation.metadata.NamedObject.user;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private AdminLoginMapper adminLoginMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
//        queryWrapper.eq("username", username);
//        User user = userMapper.selectOne(queryWrapper);
        Admin admin = adminLoginMapper.findAdminByName(username);
        System.out.println("------------");
        System.out.println(admin);

        if (admin == null) {
            throw new UsernameNotFoundException("用户不存在");
        }

        // 创建 UserDetails 对象
        return org.springframework.security.core.userdetails.User.builder()
                .username(admin.getName())
                .password(admin.getPassword())
                .authorities("ROLE_Admin")  // 为每个用户设置一个默认的权限
                .build();
    }
}
