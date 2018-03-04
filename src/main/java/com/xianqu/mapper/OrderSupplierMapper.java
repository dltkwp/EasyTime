package com.xianqu.mapper;

import com.xianqu.bean.OrderSupplier;

import java.util.List;

public interface OrderSupplierMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderSupplier record);

    int insertSelective(OrderSupplier record);

    OrderSupplier selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderSupplier record);

    int updateByPrimaryKey(OrderSupplier record);

    int insertAll(List<OrderSupplier> suppliers);

    List<OrderSupplier> selectByOrderId(Long orderId);

    void insertByList(List<OrderSupplier> suppliers);

    void deleteByOrderId(Long id);
}