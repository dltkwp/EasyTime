package com.xianqu.mapper;

import com.xianqu.entity.UserEntity;
import org.apache.ibatis.annotations.*;

import java.util.List;

public interface UserMapper {
    @Select("SELECT * FROM my_info")
    @Results({
        @Result(property = "id",  column = "id"),
        @Result(property = "name", column = "name"),
        @Result(property = "phoneNumber", column = "phone_number")
    })
    List<UserEntity> getAll();
}
