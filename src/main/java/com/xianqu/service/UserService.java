package com.xianqu.service;

import com.xianqu.bean.User;
import com.xianqu.mapper.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;

    public List<User> getAll(){
        return userMapper.selectAll();
    }

    public User getUserInfoById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public void addUser(User user) throws Exception {
        User hasUser = userMapper.selectByUsername(user.getUsername().trim());
        if(null != hasUser) {
            throw new RuntimeException("用户名已存在！");
        }
        Date date = new Date();
        user.setCreateDate(date);
        user.setUpdateDate(date);
        user.setIsDelete(false);
        int result = userMapper.insertSelective(user);
        if(result == 0) {
            throw new Exception("创建账户失败！");
        }
    }

    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}
