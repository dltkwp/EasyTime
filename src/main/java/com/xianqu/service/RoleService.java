package com.xianqu.service;

import com.xianqu.mapper.RoleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class RoleService {

    @Autowired
    private RoleMapper roleMapper;

    public List<String> findRoleById(Long id) {
        return roleMapper.findRoleByUserId(id);
    }
}
