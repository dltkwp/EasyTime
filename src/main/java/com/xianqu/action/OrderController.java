package com.xianqu.action;

import com.github.pagehelper.PageInfo;
import com.xianqu.bean.Result;
import com.xianqu.bean.User;
import com.xianqu.bean.order.OrderVo;
import com.xianqu.bean.product.ProductVo;
import com.xianqu.service.OrderService;
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
import java.util.Date;

@RestController
@CrossOrigin
@Api(value="订单设置controller",description="订单设置操作",tags={"订单设置接口"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value="订单列表", notes="获取订单列表")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/products", method = RequestMethod.GET)
    public PageInfo list(@NotNull @RequestParam("pageNum") Integer pageNum, @NotNull @RequestParam("pageSize") Integer pageSize, @RequestParam(value = "st", required = false) Date st, @RequestParam(value = "et", required = false) Date et, @RequestParam(value = "payType", required = false) String payType, @RequestParam(value = "status", required = false) String status,
                         @RequestParam(value = "content", required = false) String content, @RequestParam(value = "recipients", required = false) String recipients, @RequestParam(value = "distributor", required = false) String distributor) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        PageInfo page = orderService.getListByUserId(userSession.getId(), pageNum, pageSize, st, et, payType, status, content, recipients,distributor);
        return page;
    }

    @ApiOperation(value="新增订单", notes="新增订单")

    @RequestMapping(value="/order", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header"),
    })

    public Result add(@RequestBody OrderVo orderVo) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        orderService.insert(orderVo, userSession.getId());
        return ResultUtil.success();
    }

    @ApiOperation(value="订单详情", notes="订单详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header"),
        @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value="/order/{orderId}", method = RequestMethod.GET)
    public OrderVo get(@PathVariable("orderId") Long orderId) throws Exception {
        OrderVo orderVo = orderService.selectByPrimaryKey(orderId);
        return orderVo;
    }

    @ApiOperation(value="更新订单", notes="更新订单")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/order", method = RequestMethod.PUT)
    public Result update(@RequestBody OrderVo orderVo) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        orderService.update(orderVo, userSession.getId());
        return ResultUtil.success();
    }
}
