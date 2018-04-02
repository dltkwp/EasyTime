package com.xianqu.mapper;

import com.xianqu.bean.AgentUser;
import com.xianqu.bean.user.Normal;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface AgentUserMapper {
    int deleteByPrimaryKey(Long id);

    int insert(AgentUser record);

    int insertSelective(AgentUser record);

    AgentUser selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(AgentUser record);

    int updateByPrimaryKey(AgentUser record);

    List<Normal> getUserByAgentId(@Param("agentId") Long agentId, @Param("queryKey") String queryKey);
}