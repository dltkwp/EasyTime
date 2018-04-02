package com.xianqu.action;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xianqu.bean.user.Agent;
import com.xianqu.bean.Result;
import com.xianqu.bean.User;
import com.xianqu.bean.user.Normal;
import com.xianqu.bean.user.NormalVo;
import com.xianqu.bean.user.UserVo;
import com.xianqu.service.UserService;
import com.xianqu.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@RestController
@CrossOrigin
@Api(value="用户controller",description="用户操作",tags={"用户接口"})
public class UserController {
    private static final String SALT = "xianqu";

    @Autowired
    private UserService userService;

    @ApiOperation(value="注册新用户", notes="根据用户名密码创建用户")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "username", value = "用户名", required = true, dataType = "String", paramType = "query"),
        @ApiImplicitParam(name = "password", value = "密码", required = true, dataType = "String", paramType = "query")
    })
    @RequestMapping(value="/user", method = RequestMethod.POST)
    public Result addUser(@ApiIgnore @RequestBody User user) throws Exception {
        String password= new SimpleHash("MD5",user.getPassword(), ByteSource.Util.bytes(user.getUsername() + SALT),2).toHex();
        user.setPassword(password);
        user.setIsDelete(false);
        Date nowDate = new Date();
        user.setUpdateDate(nowDate);
        user.setCreateDate(nowDate);
        userService.addUser(user);
        return ResultUtil.success();
    }

    @ApiOperation(value="获取登录用户信息", notes="获取登录用户信息")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/user", method = RequestMethod.GET)
    public User getCurrentUser() throws Exception { User user = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        User userVo = userService.getUserInfoById(user.getId());
        if(null == userVo) {
            throw new RuntimeException("未查询到用户信息！");
        }
        userVo.setPassword(null);
        return userVo;
    }

    @ApiOperation(value="更新用户信息", notes="更新登录用户信息")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/user", method = RequestMethod.PUT)
    public Result update(@RequestBody User user) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        user.setId(userSession.getId());
        userService.updateByPrimaryKeySelective(user);
        return ResultUtil.success();
    }

    @ApiOperation(value="添加分销商", notes="添加分销商")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/user/dealer", method = RequestMethod.POST)
    public Result addDealer(@RequestBody UserVo userVo) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        Long pid = userSession.getId();
        String password= new SimpleHash("MD5",userVo.getPassword(), ByteSource.Util.bytes(userVo.getUsername() + SALT),2).toHex();
        userVo.setPassword(password);
        userService.addDealer(userVo, pid);
        return ResultUtil.success();
    }

    @ApiOperation(value="查看分销商", notes="查看分销商")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/user/dealer", method = RequestMethod.GET)
    public PageInfo getAgent(@NotNull @RequestParam("pageNum") Integer pageNum, @NotNull @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "queryKey",  required = false) String queryKey, @RequestParam(value = "levelId", required = false) Long levelId) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        PageHelper.startPage(pageNum, pageSize);
        List<Agent> userList = userService.getUserByPid(userSession.getId(), queryKey, levelId);
        PageInfo page = new PageInfo(userList);
        return page;
    }

    @ApiOperation(value="添加客户", notes="添加客户")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/user/normal", method = RequestMethod.POST)
    public Result addNormal(@RequestBody NormalVo normalVo) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        Long pid = userSession.getId();
        String password= new SimpleHash("MD5", "12345678", ByteSource.Util.bytes(normalVo.getRecipientsPhone() + SALT),2).toHex();
        userService.addNormal(normalVo, password, pid);
        return ResultUtil.success();
    }

    @ApiOperation(value="查看客户", notes="查看客户")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/user/normal", method = RequestMethod.GET)
    public PageInfo getNormal(@NotNull @RequestParam("pageNum") Integer pageNum, @NotNull @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "queryKey",  required = false) String queryKey) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        PageHelper.startPage(pageNum, pageSize);
        List<Normal> userList = userService.getUserByAgentId(userSession.getId(), queryKey);
        PageInfo page = new PageInfo(userList);
        return page;
    }
}
