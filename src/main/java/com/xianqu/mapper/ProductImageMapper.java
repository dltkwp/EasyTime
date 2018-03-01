package com.xianqu.mapper;

import com.xianqu.bean.ProductImage;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductImageMapper {
    int deleteByPrimaryKey(Long id);

    int insert(ProductImage record);

    int insertSelective(ProductImage record);

    ProductImage selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(ProductImage record);

    int updateByPrimaryKey(ProductImage record);

    void insertByList(@Param("imageList") List<ProductImage> imageList);

    void deleteByProductId(Long productId);

    List<ProductImage> selectByProductId(Long productId);
}