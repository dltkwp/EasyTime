package com.xianqu.mapper;

import com.xianqu.bean.user.Agent;
import com.xianqu.bean.Relationship;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface RelationshipMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Relationship record);

    int insertSelective(Relationship record);

    Relationship selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Relationship record);

    int updateByPrimaryKey(Relationship record);

    List<Agent> getUserByPid(@Param("pid") Long pid, @Param("queryKey") String queryKey, @Param("levelId") Long levelId);

    List<Relationship> getUserByAgentId(Long agentId);

    List<Relationship> getUserByLevelId(Long levelId);
}