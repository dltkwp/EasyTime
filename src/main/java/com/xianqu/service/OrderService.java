package com.xianqu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xianqu.bean.*;
import com.xianqu.bean.order.OrderVo;
import com.xianqu.mapper.*;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2018/1/29.
 */
@Service
@Transactional
public class OrderService {
    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private OrderSupplierMapper orderSupplierMapper;

    @Autowired
    private OrderExpressMapper orderExpressMapper;

    public Long insert(OrderVo orderVo, Long userId) {
        Order orderDto = new Order();
        Date now = new Date();
        orderVo.setCreateUser(userId);
        orderVo.setUpdateUser(userId);
        orderVo.setCreateDate(now);
        orderVo.setUpdateDate(now);
        BeanUtils.copyProperties(orderVo, orderDto);
        Long orderId = orderMapper.insert(orderDto);
        List<OrderSupplier> suppliers = orderVo.getOrderSupplierList();
        if (null != suppliers && suppliers.size() != 0) {
            for(OrderSupplier supplier: suppliers) {
                supplier.setOrderId(orderId);
                supplier.setCreateUser(userId);
                supplier.setUpdateUser(userId);
                supplier.setCreateDate(now);
                supplier.setUpdateDate(now);
            }
            orderSupplierMapper.insertByList(suppliers);
        }

        return orderId;
    }

    public PageInfo getListByUserId(Long userId, Integer pageNumber, Integer pageSize, Date st, Date et, String payType, String status, String content, String recipients, String distributor, Boolean isSupplier) {
        PageHelper.startPage(pageNumber, pageSize);
        List<OrderVo> list = orderMapper.selectByUserId(userId, st, et,payType, status, content, recipients, distributor, isSupplier);
        PageInfo page = new PageInfo(list);
        return page;
    }

    public OrderVo selectByPrimaryKey(Long orderId) {
        OrderVo orderVo = new OrderVo();
        Order order = orderMapper.selectByPrimaryKey(orderId);
        BeanUtils.copyProperties(order, orderVo);
        List<OrderSupplier> suppliers = orderSupplierMapper.selectByOrderId(orderId);
        orderVo.setOrderSupplierList(suppliers);
        return orderVo;
    }

    public void update(OrderVo orderVo, Long userId) {
        Order orderDto = new Order();
        Date now = new Date();
        orderDto.setUpdateUser(userId);
        orderDto.setUpdateDate(now);
        BeanUtils.copyProperties(orderVo, orderDto);
        orderMapper.updateByPrimaryKeySelective(orderDto);
        List<OrderSupplier> suppliers = orderVo.getOrderSupplierList();

        if (null != suppliers && suppliers.size() != 0) {
            orderSupplierMapper.deleteByOrderId(orderVo.getId());
            for(OrderSupplier supplier: suppliers) {
                supplier.setCreateUser(userId);
                supplier.setUpdateUser(userId);
                supplier.setCreateDate(now);
                supplier.setUpdateDate(now);
            }
            orderSupplierMapper.insertByList(suppliers);
        }

    }

    public void delivery(Long userId, Long orderId, String company, String expressOrder) {
        Date now = new Date();
        Order order = new Order();
        order.setId(orderId);
        order.setStatus("DELIVERY");
        order.setUpdateUser(userId);
        order.setUpdateDate(now);
        orderMapper.updateByPrimaryKeySelective(order);
        OrderExpress orderExpress = new OrderExpress();
        orderExpress.setOrderId(orderId);
        orderExpress.setCompany(company);
        orderExpress.setExpressOrder(expressOrder);
        orderExpress.setCreateUser(userId);
        orderExpress.setCreateDate(now);
        orderExpressMapper.insert(orderExpress);
    }
}
