package com.xianqu.mapper;

import com.xianqu.bean.OrderPaymentRecord;

public interface OrderPaymentRecordMapper {
    int deleteByPrimaryKey(Long id);

    int insert(OrderPaymentRecord record);

    int insertSelective(OrderPaymentRecord record);

    OrderPaymentRecord selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderPaymentRecord record);

    int updateByPrimaryKey(OrderPaymentRecord record);
}