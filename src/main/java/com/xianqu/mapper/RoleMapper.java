package com.xianqu.mapper;

import com.xianqu.bean.Role;
import org.apache.ibatis.annotations.Param;

public interface RoleMapper {
    Role findRoleById(@Param(value = "uid") Integer userId);
}
