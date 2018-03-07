package com.xianqu.action;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xianqu.bean.Order;
import com.xianqu.bean.OrderExpress;
import com.xianqu.bean.Result;
import com.xianqu.bean.User;
import com.xianqu.bean.order.OrderVo;
import com.xianqu.service.OrderService;
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
import java.util.List;

@RestController
@CrossOrigin
@Api(value="订单设置controller",description="订单设置操作",tags={"订单设置接口"})
public class OrderController {

    @Autowired
    private OrderService orderService;

    @ApiOperation(value="订单列表", notes="获取订单列表")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/orders", method = RequestMethod.GET)
    public PageInfo list(@NotNull @RequestParam("pageNum") Integer pageNum, @NotNull @RequestParam("pageSize") Integer pageSize,
                         @RequestParam(value = "st", required = false) Date st, @RequestParam(value = "et", required = false) Date et,
                         @RequestParam(value = "payType", required = false) String payType, @RequestParam(value = "status", required = false) String status,
                         @RequestParam(value = "content", required = false) String content, @RequestParam(value = "recipients", required = false) String recipients,
                         @RequestParam(value = "distributor", required = false) String distributor,@NotNull @RequestParam(value = "isSupplier") Boolean isSupplier) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        PageHelper.startPage(pageNum, pageSize);
        List<OrderVo> list = orderService.getListByUserId(userSession.getId(), st, et, payType, status, content, recipients, distributor, isSupplier);
        PageInfo page = new PageInfo(list);
        return page;
    }

    @ApiOperation(value="新增订单", notes="新增订单")
    @RequestMapping(value="/orders", method = RequestMethod.POST)
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header"),
    })
    public Result add(@RequestBody OrderVo orderVo) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        orderVo.setStatus("WAIT");
        orderService.insert(orderVo, userSession.getId());
        return ResultUtil.success();
    }

    @ApiOperation(value="订单详情", notes="订单详情")
    @ApiImplicitParams({
        @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header"),
        @ApiImplicitParam(name = "orderId", value = "订单ID", required = true, dataType = "String", paramType = "path")
    })
    @RequestMapping(value="/orders/{orderId}", method = RequestMethod.GET)
    public OrderVo get(@PathVariable("orderId") Long orderId) throws Exception {
        OrderVo orderVo = orderService.selectByPrimaryKey(orderId);
        return orderVo;
    }

    @ApiOperation(value="更新订单", notes="更新订单")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/orders", method = RequestMethod.PUT)
    public Result update(@RequestBody OrderVo orderVo) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        orderService.update(orderVo, userSession.getId());
        return ResultUtil.success();
    }

    @ApiOperation(value="发货", notes="发货")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/delivery", method = RequestMethod.POST)
    public void delivery(@NotNull @RequestParam(value = "orderId") Long orderId, @NotNull @RequestParam(value = "company") String company, @NotNull @RequestParam(value = "expressOrder") String expressOrder) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        orderService.delivery(userSession.getId(), orderId, company, expressOrder);
    }

    @ApiOperation(value="取得发货信息", notes="取得发货信息")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/delivery", method = RequestMethod.GET)
    public OrderExpress deliveryInfo(@NotNull @RequestParam(value = "orderId") Long orderId) throws Exception {
        OrderExpress orderExpress = orderService.deliveryInfo(orderId);
        return orderExpress;
    }

    @ApiOperation(value="发货", notes="发货")
    @ApiImplicitParam(name = "Authorization", value = "鉴权", required = true, dataType = "String", paramType = "header")
    @RequestMapping(value="/delivery", method = RequestMethod.PUT)
    public void deliveryUpdate(@NotNull @RequestParam(value = "id") Long id, @NotNull @RequestParam(value = "orderId") Long orderId, @NotNull @RequestParam(value = "company") String company, @NotNull @RequestParam(value = "expressOrder") String expressOrder) throws Exception {
        User userSession = (User) SecurityUtils.getSubject().getSession().getAttribute("user");
        orderService.updateDelivery(userSession.getId(), id, orderId, company, expressOrder);
    }
}
