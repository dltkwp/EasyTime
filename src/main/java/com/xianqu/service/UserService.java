package com.xianqu.service;

import com.xianqu.bean.User;
import com.xianqu.mapper.UserMapper;
import com.xianqu.vo.UserVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getAll(){
        return userMapper.getAll();
    }

    public UserVo findUserByName(String username) {
        return userMapper.findUserByName(username);
    }
}
