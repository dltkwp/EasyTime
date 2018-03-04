package com.xianqu.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.xianqu.bean.*;
import com.xianqu.bean.order.OrderVo;
import com.xianqu.bean.product.ProductVo;
import com.xianqu.mapper.*;
import org.apache.commons.lang.ArrayUtils;
import org.apache.ibatis.reflection.ArrayUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

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
                supplier.setCreateUser(userId);
                supplier.setUpdateUser(userId);
                supplier.setCreateDate(now);
                supplier.setUpdateDate(now);
            }
            orderSupplierMapper.insertByList(suppliers);
        }

        return orderId;
    }

    public PageInfo getListByUserId(Long userId, Integer pageNumber, Integer pageSize, Date st, Date et, String payType, String status, String content, String recipients, String distributor) {
        PageHelper.startPage(pageNumber, pageSize);
        List<OrderVo> list = orderMapper.selectByUserId(userId, content, st, et, payType, status, recipients, distributor);
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
}
