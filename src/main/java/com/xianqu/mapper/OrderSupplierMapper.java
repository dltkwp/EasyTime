package com.xianqu.mapper;

import com.xianqu.bean.OrderSupplier;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface OrderSupplierMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderSupplier record);

    int insertSelective(OrderSupplier record);

    OrderSupplier selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderSupplier record);

    int updateByPrimaryKey(OrderSupplier record);

    List<OrderSupplier> selectByOrderId(Long orderId);

    void insertByList(@Param("supplierList") List<OrderSupplier> supplierList);

    void deleteByOrderId(Long id);
}