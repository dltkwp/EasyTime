package com.xianqu.action;

import com.xianqu.bean.User;
import com.xianqu.service.UserService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.Md5Hash;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.websocket.server.PathParam;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@Api(value="用户controller",description="用户操作",tags={"用户接口"})
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value="注册新用户", notes="根据用户名密码创建用户")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "phone", value = "电话", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value="/user", method = RequestMethod.POST)
    public void addUser(@ApiIgnore @RequestBody User user) throws Exception {
        String salt = "xianqu";
        String password= new SimpleHash("MD5",user.getPassword(), ByteSource.Util.bytes(user.getUsername() + "xianqu"),2).toHex();
        user.setPassword(password);
        user.setIsDelete(false);
        Date nowDate = new Date();
        user.setUpdateDate(nowDate);
        user.setCreateDate(nowDate);
        userService.addUser(user);
    }

    @ApiOperation(value="获取登录用户信息", notes="获取登录用户信息")
    @RequestMapping(value="/user", method = RequestMethod.GET)
    public User getCurrentUser() throws Exception {
        Long id = (Long) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        User userVo = userService.getUserInfoById(id);
        if(null == userVo) {
            throw new RuntimeException("未查询到用户信息！");
        }
        userVo.setPassword(null);
        return userVo;
    }

    @ApiOperation(value="更新用户信息", notes="更新登录用户信息")
    @RequestMapping(value="/user", method = RequestMethod.PUT)
    public void update(@RequestBody User user) throws Exception {
        Long id = (Long) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        user.setId(id);
        userService.updateByPrimaryKeySelective(user);
    }
}
