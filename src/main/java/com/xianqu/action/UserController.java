package com.xianqu.action;

import com.xianqu.entity.UserEntity;
import com.xianqu.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value = "/getUsers", method = RequestMethod.GET)
    public List<UserEntity> getUsers() {
        List<UserEntity> users = userService.getAll();
        return users;
    }
}
