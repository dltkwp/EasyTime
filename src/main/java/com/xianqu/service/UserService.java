package com.xianqu.service;

import com.xianqu.bean.Agent;
import com.xianqu.bean.Relationship;
import com.xianqu.bean.User;
import com.xianqu.bean.UserRole;
import com.xianqu.bean.user.UserVo;
import com.xianqu.mapper.RelationshipMapper;
import com.xianqu.mapper.UserMapper;
import com.xianqu.mapper.UserRoleMapper;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class UserService {
    @Autowired
    private UserMapper userMapper;

    @Autowired
    private UserRoleMapper userRoleMapper;

    @Autowired
    private RelationshipMapper relationshipMapper;

    public List<User> getAll(){
        return userMapper.selectAll();
    }

    public User getUserInfoById(Long id) {
        return userMapper.selectByPrimaryKey(id);
    }

    public void addUser(User user) throws Exception {
        User hasUser = userMapper.selectByUsername(user.getUsername().trim());
        if(null != hasUser) {
            throw new RuntimeException("用户名已存在！");
        }
        Date date = new Date();
        user.setCreateDate(date);
        user.setUpdateDate(date);
        user.setIsDelete(false);
        Long userId = userMapper.insertSelective(user);

        UserRole userRole = new UserRole();
        userRole.setUid(userId);
        userRole.setRid(2L);
        userRoleMapper.insert(userRole);
    }

    public User findByUsername(String username) {
        return userMapper.selectByUsername(username);
    }

    public int updateByPrimaryKeySelective(User user) { return userMapper.updateByPrimaryKeySelective(user);}

    public void addDealer(UserVo userVo, Long pid) {
        User user = new User();
        user.setIsDelete(false);
        Date nowDate = new Date();
        user.setUpdateDate(nowDate);
        user.setCreateDate(nowDate);
        BeanUtils.copyProperties(userVo, user);
        userMapper.insert(user);
        Long userId = user.getId();
        UserRole userRole = new UserRole();
        userRole.setUid(userId);
        userRole.setRid(2L);
        userRoleMapper.insert(userRole);
        Relationship relationship = new Relationship();
        relationship.setUid(userId);
        relationship.setPid(pid);
        relationship.setDistributorLevelId(userVo.getLevelId());
        relationshipMapper.insert(relationship);
    }

    public List<Agent> getUserByPid(Long pid, String queryKey, Long levelId) {
        return relationshipMapper.getUserByPid(pid, queryKey, levelId);
    }
}
