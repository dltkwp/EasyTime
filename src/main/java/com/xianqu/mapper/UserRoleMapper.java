package com.xianqu.mapper;

import com.xianqu.bean.Role;
import com.xianqu.bean.UserRole;

import java.util.List;

public interface UserRoleMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRole record);

    int insertSelective(UserRole record);

    UserRole selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRole record);

    int updateByPrimaryKey(UserRole record);

    List<String> findRoleByUserId(Long userId);

    List<Role> getRoleByUserName(String username);
}