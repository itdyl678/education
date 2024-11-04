package com.itflyket.education.service.Imp;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.itflyket.education.entity.User;
import com.itflyket.education.mapper.GetUserAllMapper;
import com.itflyket.education.service.GetUserAllService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

@Service
public class GetUserAllServiceImp implements GetUserAllService {

    @Autowired
    private GetUserAllMapper getUserAllMapper;

    /**
     * 时间处理
     * @return
     */
    @Override
    public List<User> getUserAll() {
        List<User> users = this.getUserAllMapper.getUserAll();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");

        for (User user : users) {
            if (user.getCreatedAt() != null) {
                user.setFormattedCreatedAt(sdf.format(user.getCreatedAt()));
            }
            if (user.getUpdatedAt() != null) {
                user.setFormattedUpdatedAt(sdf.format(user.getUpdatedAt()));
            }
        }

        return users;
        }


    /**
     * 分页查询
     * @param currentPage
     * @param pageSize
     * @return
     */
    public IPage<User> getUserPage(Integer currentPage, Integer pageSize) {
        Page<User> page = new Page<>(currentPage, pageSize); // 创建分页对象，设置当前页和每页大小
        QueryWrapper<User> queryWrapper = new QueryWrapper<>(); // 创建查询条件包装器
        // 如果需要，可以在这里添加查询条件
        return getUserAllMapper.selectPage(page, queryWrapper); // 执行分页查询
    }

}
