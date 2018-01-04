package com.xianqu.action;

import com.xianqu.bean.User;
import com.xianqu.service.UserService;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @RequestMapping(value="/user", method = RequestMethod.POST)
    public void addUser(@RequestBody User user) throws Exception {
        String salt = "xianqu";
        String password= new SimpleHash("MD5",user.getPassword(), ByteSource.Util.bytes(user.getUsername() + "xianqu"),2).toHex();
        user.setPassword(password);
        user.setIsDelete(false);
        Date nowDate = new Date();
        user.setUpdateDate(nowDate);
        user.setCreateDate(nowDate);
        userService.addUser(user);
    }
}
