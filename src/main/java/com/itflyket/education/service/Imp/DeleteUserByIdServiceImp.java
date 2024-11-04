package com.itflyket.education.service.Imp;

import com.itflyket.education.mapper.DeleteUserByIdMapper;
import com.itflyket.education.service.DeleteUserByIdService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DeleteUserByIdServiceImp implements DeleteUserByIdService {
    @Autowired
    private DeleteUserByIdMapper deleteUserByIdMapper;

    /**
     * 更具id删除用户信息
     * @param id
     */
    @Override
    public void deleteUserById(Long id) {
        this.deleteUserByIdMapper.deleteUserById(id);
    }
}
