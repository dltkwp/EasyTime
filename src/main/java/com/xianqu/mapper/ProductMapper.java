package com.xianqu.mapper;

import com.xianqu.bean.Product;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductMapper {
    int deleteByPrimaryKey(Long id);

    Long insert(Product record);

    int insertSelective(Product record);

    Product selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Product record);

    int updateByPrimaryKey(Product record);

    Product selectByName(@Param("productName") String productName, @Param("userId") Long userId);

    List<Product> selectByUserId(@Param("userId") Long userId, @Param("productName") String productName, @Param("categoriesId") Long categoriesId, @Param("status") Boolean status);
}