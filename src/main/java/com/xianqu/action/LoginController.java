package com.xianqu.action;

import com.alibaba.fastjson.JSONObject;
import com.xianqu.bean.*;
import com.xianqu.service.RoleService;
import com.xianqu.service.UserService;
import com.xianqu.service.WxLoginService;
import com.xianqu.util.ResultUtil;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Api(value="登录登出修改密码controller",description="登录登出修改密码操作",tags={"登录登出修改密码接口"})
public class LoginController {

    private static final String SALT = "xianqu";

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ApiOperation(value="登录", notes="根据用户名和密码登录")
    @ApiResponses({
        @ApiResponse(code = 1000200, message = "登录成功")
    })
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    public String login(@ApiIgnore @RequestBody User userInfo) throws Exception{
        JSONObject jsonObject = new JSONObject();
        Subject subject = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(userInfo.getUsername(), userInfo.getPassword());
        subject.login(token);
        List<Role> list = roleService.getRoleByUserName(userInfo.getUsername());
        jsonObject.put("roleList", list);
        jsonObject.put("token", subject.getSession().getId());
        jsonObject.put("code", 1000200);
        jsonObject.put("msg", "登录成功");
        return jsonObject.toString();
    }

    @ApiIgnore
    @RequestMapping(value = "/unauth")
    public Object unauth() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("code", 1000000);
        map.put("msg", "未登录");
        return map;
    }

    @ApiOperation(value="登出", notes="退出登录")
    @ApiResponses({
        @ApiResponse(code = 1000201, message = "已退出登录")
    })
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public Object logout() {
        Map<String, Object> map = new HashMap<String, Object>();
        Subject subject = SecurityUtils.getSubject();
        if (subject.isAuthenticated()) {
            subject.logout();
        }
        map.put("code", 1000201);
        map.put("msg", "已退出登录");
        return map;
    }

    @ApiOperation(value="修改密码", notes="修改密码")
    @RequestMapping(value = "/updatePassword", method = RequestMethod.POST)
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    public Result updatePassword(@RequestBody PasswordVo passwordVo) {
        if(!passwordVo.getNewPassword1().equals(passwordVo.getNewPassword2())) {
            throw new RuntimeException("两次输入密码不一致");
        }
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        User cur = userService.getUserInfoById(userSession.getId());
        String password= new SimpleHash("MD5", passwordVo.getOldPassword(), ByteSource.Util.bytes(cur.getUsername() + SALT),2).toHex();
        if(!password.equals(cur.getPassword())) {
            throw new RuntimeException("密码错误");
        }

        String newPassword= new SimpleHash("MD5", passwordVo.getNewPassword1(), ByteSource.Util.bytes(cur.getUsername() + SALT),2).toHex();
        User newUser = new User();
        newUser.setId(userSession.getId());
        newUser.setPassword(newPassword);
        userService.updateByPrimaryKeySelective(newUser);
        return ResultUtil.success();
    }
}
