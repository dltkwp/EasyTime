package com.xianqu.mapper;

import com.xianqu.bean.DistributorLevel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface DistributorLevelMapper {
    int deleteByPrimaryKey(Long id);

    int insert(DistributorLevel record);

    int insertSelective(DistributorLevel record);

    DistributorLevel selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(DistributorLevel record);

    int updateByPrimaryKey(DistributorLevel record);

    DistributorLevel selectByName(@Param("levelName") String levelName, @Param("userId") Long userId);

    List<DistributorLevel> getListByUserId(Long userId);
}