package com.xianqu.mapper;

import com.xianqu.bean.Categories;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CategoriesMapper {
    int deleteByPrimaryKey(Long id);

    int insert(Categories record);

    int insertSelective(Categories record);

    Categories selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(Categories record);

    int updateByPrimaryKey(Categories record);

    List<Categories> getListByUserId(Long userId);

    Categories selectByName(@Param("categoriesName") String categoriesName, @Param("userId") Long userId);
}