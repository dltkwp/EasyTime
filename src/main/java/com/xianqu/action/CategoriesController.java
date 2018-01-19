package com.xianqu.action;

import com.xianqu.bean.Categories;
import com.xianqu.service.CategoriesService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import springfox.documentation.annotations.ApiIgnore;

import javax.websocket.server.PathParam;
import java.util.List;

@RestController
@CrossOrigin
@Api(value="分类设置controller",description="分类设置操作",tags={"分类设置接口"})
public class CategoriesController {

    @Autowired
    private CategoriesService categoriesService;

    @ApiOperation(value="分类列表", notes="获取分类列表")
    @RequestMapping(value="/categories", method = RequestMethod.GET)
    public List<Categories> list() throws Exception {
        Long id = (Long) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        List<Categories> list = categoriesService.getListByUserId(id);
        return list;
    }

    @ApiOperation(value="新增分类", notes="新增分类")
    @RequestMapping(value="/categories", method = RequestMethod.POST)
    @ApiImplicitParam(name = "categoriesName", value = "分类名称", required = true, dataType = "String", paramType = "body")
    public void add(@ApiIgnore @RequestBody Categories categories) throws Exception {
        Long id = (Long) SecurityUtils.getSubject().getSession().getAttribute("userSessionId");
        categories.setUserId(id);
        categoriesService.insert(categories);
    }

    @ApiOperation(value="分类详情", notes="分类详情")
    @ApiImplicitParam(name = "categoriesId", value = "分类ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/categories/{categoriesId}", method = RequestMethod.GET)
    public Categories get(@PathParam("categoriesId") Long categoriesId) throws Exception {
        Categories categories = categoriesService.selectByPrimaryKey(categoriesId);
        return categories;
    }

    @ApiOperation(value="删除分类", notes="删除分类")
    @ApiImplicitParam(name = "categoriesId", value = "分类ID", required = true, dataType = "String", paramType = "path")
    @RequestMapping(value="/categories/{categoriesId}", method = RequestMethod.DELETE)
    public void delete(@PathParam("categoriesId") Long categoriesId) throws Exception {
        categoriesService.delete(categoriesId);
    }

    @ApiOperation(value="更新分类", notes="更新分类")
    @RequestMapping(value="/categories", method = RequestMethod.PUT)
    public void update(@RequestBody Categories categories) throws Exception {
        categoriesService.update(categories);
    }
}
