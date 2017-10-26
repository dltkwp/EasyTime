package com.xianqu.service;

import com.xianqu.entity.UserEntity;
import com.xianqu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<UserEntity> getAll(){
        return userMapper.getAll();
    }
}
