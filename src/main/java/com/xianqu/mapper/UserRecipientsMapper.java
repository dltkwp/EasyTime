package com.xianqu.mapper;

import com.xianqu.bean.UserRecipients;

public interface UserRecipientsMapper {
    int deleteByPrimaryKey(Long id);

    int insert(UserRecipients record);

    int insertSelective(UserRecipients record);

    UserRecipients selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(UserRecipients record);

    int updateByPrimaryKey(UserRecipients record);
}