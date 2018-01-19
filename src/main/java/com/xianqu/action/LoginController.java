package com.xianqu.action;

import com.alibaba.fastjson.JSONObject;
import com.xianqu.bean.User;
import io.swagger.annotations.*;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin
@Api(value="登录登出controller",description="登录登出操作",tags={"登录登出接口"})
public class LoginController {


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
}
