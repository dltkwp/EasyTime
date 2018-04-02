package com.xianqu.service;

import com.xianqu.bean.*;
import com.xianqu.bean.user.Agent;
import com.xianqu.bean.user.Normal;
import com.xianqu.bean.user.NormalVo;
import com.xianqu.bean.user.UserVo;
import com.xianqu.mapper.*;
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

    @Autowired
    private AgentUserMapper agentUserMapper;

    @Autowired
    private UserRecipientsMapper userRecipientsMapper;

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

        Relationship relationship = new Relationship();
        relationship.setPid(1L);
        relationship.setUid(userId);
        relationship.setDistributorLevelId(1L);
        relationshipMapper.insert(relationship);
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
        userMapper.insertSelective(user);
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

    public void addNormal(NormalVo normalVo, String password, Long pid) {
        User user = new User();
        user.setUsername(normalVo.getRecipientsPhone());
        user.setRealname(normalVo.getRealname());
        user.setComment(normalVo.getComment());
        user.setPassword(password);
        user.setIsDelete(false);
        Date nowDate = new Date();
        user.setUpdateDate(nowDate);
        user.setCreateDate(nowDate);
        userMapper.insertSelective(user);
        Long userId = user.getId();
        UserRole userRole = new UserRole();
        userRole.setUid(userId);
        userRole.setRid(3L);
        userRoleMapper.insert(userRole);
        AgentUser agentUser = new AgentUser();
        agentUser.setUid(userId);
        agentUser.setPid(pid);
        agentUserMapper.insert(agentUser);
        UserRecipients userRecipients = new UserRecipients();
        userRecipients.setIsDefault(true);
        userRecipients.setUserId(userId);
        userRecipients.setRecipientsPhone(normalVo.getRecipientsPhone());
        userRecipients.setRecipientsAddress(normalVo.getRecipientsAddress());
        userRecipientsMapper.insert(userRecipients);
    }

    public List<Normal> getUserByAgentId(Long id, String queryKey) {
        return agentUserMapper.getUserByAgentId(id, queryKey);
    }
}
