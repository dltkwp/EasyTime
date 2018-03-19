package com.xianqu.mapper;

import com.xianqu.bean.Product;
import com.xianqu.bean.product.ProductListVo;
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

    List<ProductListVo> selectByUserId(@Param("userId") Long userId, @Param("queryKey") String queryKey, @Param("categoriesId") Long categoriesId, @Param("status") Boolean status);
}