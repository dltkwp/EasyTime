package com.xianqu.config;

import com.xianqu.bean.Role;
import com.xianqu.service.UserService;
import com.xianqu.vo.UserVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;

public class MyUserDetailsService implements UserDetailsService {

    private static final Logger log = LoggerFactory.getLogger(com.xianqu.config.MyUserDetailsService.class);

    @Autowired
    private UserService userService;

    /**
     * 根据用户名获取登录用户信息
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserVo user = userService.findUserByName(username);

        if(user == null){
            throw new UsernameNotFoundException("用户名："+ username + "不存在！");
        }
        Collection<SimpleGrantedAuthority> collection = new HashSet<SimpleGrantedAuthority>();
        List<Role> roles =  user.getRolelist();
        for (Role role : roles){
            collection.add(new SimpleGrantedAuthority(role.getRoleName()));
        }

        return new org.springframework.security.core.userdetails.User(username,user.getPassword(),collection);
    }

}
