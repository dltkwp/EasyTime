package com.xianqu.mapper;

import com.xianqu.bean.Role;

import java.util.List;

public interface RoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Role record);

    int insertSelective(Role record);

    Role selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Role record);

    int updateByPrimaryKey(Role record);

    List<String> findRoleByUserId(Long userId);

    List<Role> getRoleByUserName(String username);
}