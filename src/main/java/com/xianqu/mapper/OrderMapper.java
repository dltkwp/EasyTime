package com.xianqu.mapper;

import com.xianqu.bean.Order;
import com.xianqu.bean.order.OrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

public interface OrderMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(Order record);

    int insertSelective(Order record);

    Order selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Order record);

    int updateByPrimaryKey(Order record);

    List<OrderVo> selectByUserId(@Param("userId") Long userId, @Param("st")  Date st,@Param("et")  Date et,@Param("payType")  String payType, @Param("queryKey") String queryKey, @Param("status") String status, @Param("distributor") String distributor,@Param("agents") String agents,@Param("isSupplier") Boolean isSupplier);
}