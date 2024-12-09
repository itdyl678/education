package com.itflyket.education.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.itflyket.education.entity.User;

public interface UserMapper extends BaseMapper<User> {
    // 由于继承了 BaseMapper，可以直接使用内置的 CRUD 方法
}
