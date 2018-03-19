package com.xianqu.action;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xianqu.bean.Result;
import com.xianqu.bean.User;
import com.xianqu.bean.product.ProductListVo;
import com.xianqu.bean.product.ProductVo;
import com.xianqu.service.ProductService;
import com.xianqu.util.ResultUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.apache.shiro.SecurityUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@CrossOrigin
@Api(value="商品设置controller",description="商品设置操作",tags={"商品设置接口"})
public class ProductController {

    @Autowired
    private ProductService productService;

    @ApiOperation(value="商品列表", notes="获取商品列表")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/products", method = RequestMethod.GET)
    public PageInfo list(@NotNull @RequestParam("pageNum") Integer pageNum,@NotNull @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "queryKey", defaultValue = "") String queryKey, @RequestParam(value = "categoriesId", required = false) Long categoriesId, @RequestParam(value = "status", required = false) Boolean status) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        PageHelper.startPage(pageNum, pageSize);
        List<ProductListVo> list = productService.getListByUserId(userSession.getId(), queryKey, categoriesId, status);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @ApiOperation(value="新增商品", notes="新增商品")

    @RequestMapping(value="/products", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header"),
    })

    public Map<String, Object> add(@RequestBody ProductVo productVo) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        Long productId = productService.insert(productVo, userSession.getId());
        Map<String, Object> map = new HashMap<>();
        map.put("productId", productId);
        return map;
    }

    @ApiOperation(value="商品详情", notes="商品详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header"),
        @ApiImplicitParam(name = "productId", value = "商品ID", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value="/products/{productId}", method = RequestMethod.GET)
    public ProductVo get(@PathVariable("productId") Long productId) throws Exception {
        ProductVo productVo = productService.selectByPrimaryKey(productId);
        return productVo;
    }

    @ApiOperation(value="删除商品", notes="删除商品")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header"),
        @ApiImplicitParam(name = "productId", value = "商品ID", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value="/products/{productId}", method = RequestMethod.DELETE)
    public Result delete(@PathVariable("productId") Long productId) throws Exception {
        productService.delete(productId);
        return ResultUtil.success();
    }

    @ApiOperation(value="更新商品", notes="更新商品")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/products", method = RequestMethod.PUT)
    public Result update(@RequestBody ProductVo productVo) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        productService.update(productVo, userSession.getId());
        return ResultUtil.success();
    }
}
