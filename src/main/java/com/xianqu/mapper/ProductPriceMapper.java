package com.xianqu.mapper;

import com.xianqu.bean.ProductPrice;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductPriceMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductPrice record);

    int insertSelective(ProductPrice record);

    ProductPrice selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductPrice record);

    int updateByPrimaryKey(ProductPrice record);

    void insertByList(@Param("prices") List<ProductPrice> prices);

    void deleteByProductId(Long productId);

    List<ProductPrice> selectByProductId(Long productId);
}