package com.xianqu.service;

import com.xianqu.bean.Role;
import com.xianqu.mapper.RoleMapper;
import com.xianqu.mapper.UserRoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired RoleMapper roleMapper;

    public List<Role> getRoleByUserName(String username) {
        return roleMapper.getRoleByUserName(username);
    }
}
