package com.xianqu.action;

import com.xianqu.bean.DistributorLevel;
import com.xianqu.bean.Result;
import com.xianqu.bean.User;
import com.xianqu.service.DistributorLevelService;
import com.xianqu.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import java.util.List;

@RestController
@CrossOrigin
@Api(value="分销等级设置controller",description="分销等级设置操作",tags={"分销等级设置接口"})
public class DistributorLevelController {

    @Autowired
    private DistributorLevelService distributorLevelService;

    @ApiOperation(value="分销等级列表", notes="获取分类列表")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/distributorLevels", method = RequestMethod.GET)
    public List<DistributorLevel> list() throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<DistributorLevel> list = distributorLevelService.getListByUserId(userSession.getId());
        return list;
    }

    @ApiOperation(value="新增分销等级", notes="新增分销等级")
    @RequestMapping(value="/distributorLevels", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    })
    public Result add(@ApiIgnore @RequestBody DistributorLevel distributorLevel) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        distributorLevel.setUserId(userSession.getId());
        distributorLevelService.insert(distributorLevel);
        return ResultUtil.success();
    }

    @ApiOperation(value="分销等级详情", notes="删除分销详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header"),
        @ApiImplicitParam(name = "distributorLevelId", value = "分销等级ID", required = true, dataType = "Long", paramType = "path")
    })
    @RequestMapping(value="/distributorLevel/{distributorLevelId}", method = RequestMethod.GET)
    public DistributorLevel get(@PathVariable("distributorLevelId") Long distributorLevelId) throws Exception {
        DistributorLevel distributorLevel = distributorLevelService.selectByPrimaryKey(distributorLevelId);
        return distributorLevel;
    }

    @ApiOperation(value="删除分销等级", notes="删除分销等级")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header"),
        @ApiImplicitParam(name = "distributorLevelId", value = "分销等级ID", required = true, dataType = "Long", paramType = "path")
    })
    @RequestMapping(value="/distributorLevel/{distributorLevelId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable("distributorLevelId") Long distributorLevelId) throws Exception {
        distributorLevelService.delete(distributorLevelId);
        return ResultUtil.success();
    }

    @ApiOperation(value="更新分销等级", notes="更新分销等级")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/distributorLevel", method = RequestMethod.PUT)
    public Result update(@RequestBody DistributorLevel distributorLevel) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        distributorLevel.setUserId(userSession.getId());
        distributorLevelService.update(distributorLevel);
        return ResultUtil.success();
    }
}
