package com.xianqu.service;


import com.xianqu.bean.DistributorLevel;
import com.xianqu.mapper.DistributorLevelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DistributorLevelService {

    @Autowired
    private DistributorLevelMapper distributorLevelMapper;

    public DistributorLevel selectByPrimaryKey(Long id){
        return distributorLevelMapper.selectByPrimaryKey(id);
    }

    public List<DistributorLevel> getListByUserId(Long userId) {
        return distributorLevelMapper.getListByUserId(userId);
    }

    public int insert(DistributorLevel distributorLevel) {
        DistributorLevel hasDistributorLevel = distributorLevelMapper.selectByName(distributorLevel.getLevelName(), distributorLevel.getUserId());
        if(null != hasDistributorLevel) {
            throw new RuntimeException("等级名已存在");
        }
        return distributorLevelMapper.insert(distributorLevel);
    }

    public int delete(Long id) {
        //TODO 查询是否使用   使用抛出异常
        return distributorLevelMapper.deleteByPrimaryKey(id);
    }

    public int update(DistributorLevel distributorLevel) {
        DistributorLevel hasDistributorLevel = distributorLevelMapper.selectByName(distributorLevel.getLevelName(), distributorLevel.getUserId());
        if(null != hasDistributorLevel) {
            throw new RuntimeException("等级名已存在");
        }
        return distributorLevelMapper.updateByPrimaryKeySelective(distributorLevel);
    }
}
