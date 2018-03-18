package com.xianqu.service;

import com.xianqu.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class UserRoleService {

    @Autowired
    private UserRoleMapper userRoleMapper;

    public List<String> findRoleByUserId(Long id) {
        return userRoleMapper.findRoleByUserId(id);
    }
}
