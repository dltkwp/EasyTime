package com.xianqu.mapper;

import com.xianqu.bean.User;
import com.xianqu.vo.UserVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface UserMapper {
    List<User> getAll();

    UserVo findUserByName(@Param(value = "username") String username);
}
