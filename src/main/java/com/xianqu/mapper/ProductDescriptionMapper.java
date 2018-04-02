package com.xianqu.mapper;

import com.xianqu.bean.ProductDescription;

public interface ProductDescriptionMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductDescription record);

    int insertSelective(ProductDescription record);

    ProductDescription selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductDescription record);

    int updateByPrimaryKey(ProductDescription record);

    ProductDescription selectByProductId(Long productId);
}