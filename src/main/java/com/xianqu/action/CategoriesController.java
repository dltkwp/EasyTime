package com.xianqu.action;

import com.xianqu.bean.Categories;
import com.xianqu.bean.Result;
import com.xianqu.bean.User;
import com.xianqu.service.CategoriesService;
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
@Api(value="分类设置controller",description="分类设置操作",tags={"分类设置接口"})
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @ApiOperation(value="分类列表", notes="获取分类列表")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/categories", method = RequestMethod.GET)
    public List<Categories> list() throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        List<Categories> list = categoriesService.getListByUserId(userSession.getId());
        return list;
    }

    @ApiOperation(value="新增分类", notes="新增分类")

    @RequestMapping(value="/categories", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header"),
        @ApiImplicitParam(name = "categoriesName", value = "分类名称", required = true, dataType = "String", paramType = "body")
    })

    public Result add(@ApiIgnore @RequestBody Categories categories) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        categories.setUserId(userSession.getId());
        categoriesService.insert(categories);
        return ResultUtil.success();
    }

    @ApiOperation(value="分类详情", notes="分类详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header"),
        @ApiImplicitParam(name = "categoriesId", value = "分类ID", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value="/categories/{categoriesId}", method = RequestMethod.GET)
    public Categories get(@PathVariable("categoriesId") Long categoriesId) throws Exception {
        Categories categories = categoriesService.selectByPrimaryKey(categoriesId);
        return categories;
    }

    @ApiOperation(value="删除分类", notes="删除分类")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header"),
        @ApiImplicitParam(name = "categoriesId", value = "分类ID", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value="/categories/{categoriesId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable("categoriesId") Long categoriesId) throws Exception {
        categoriesService.delete(categoriesId);
        return ResultUtil.success();
    }

    @ApiOperation(value="更新分类", notes="更新分类")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/categories", method = RequestMethod.PUT)
    public Result update(@RequestBody Categories categories) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        categories.setUserId(userSession.getId());
        categoriesService.update(categories);
        return ResultUtil.success();
    }
}
