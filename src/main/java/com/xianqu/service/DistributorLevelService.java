package com.xianqu.service;


import com.xianqu.bean.DistributorLevel;
import com.xianqu.bean.Relationship;
import com.xianqu.mapper.DistributorLevelMapper;
import com.xianqu.mapper.RelationshipMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class DistributorLevelService {

    @Autowired
    private DistributorLevelMapper distributorLevelMapper;

    @Autowired
    private RelationshipMapper relationshipMapper;

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
        List<Relationship> list = relationshipMapper.getUserByLevelId(id);
        if(null != list && list.size() > 0) {
            throw new RuntimeException("等级已使用，请更换分销商等级后再试");
        }
        return distributorLevelMapper.deleteByPrimaryKey(id);
    }

    public int update(DistributorLevel distributorLevel) {
        DistributorLevel hasDistributorLevel = distributorLevelMapper.selectByName(distributorLevel.getLevelName(), distributorLevel.getUserId());
        if(null != hasDistributorLevel && !hasDistributorLevel.getId().equals(distributorLevel.getId())) {
            throw new RuntimeException("等级名已存在");
        }
        return distributorLevelMapper.updateByPrimaryKeySelective(distributorLevel);
    }
}
