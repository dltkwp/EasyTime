package com.xianqu.service;


import com.xianqu.bean.Categories;
import com.xianqu.mapper.CategoriesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class CategoriesService {

    @Autowired
    private CategoriesMapper categoriesMapper;

    public Categories selectByPrimaryKey(Long id){
        return categoriesMapper.selectByPrimaryKey(id);
    }

    public List<Categories> getListByUserId(Long userId) {
        return categoriesMapper.getListByUserId(userId);
    }

    public int insert(Categories categories) {
        return categoriesMapper.insert(categories);
    }

    public int delete(Long id) {
        //TODO 查询是否使用   使用抛出异常
        return categoriesMapper.deleteByPrimaryKey(id);
    }

    public int update(Categories categories) {
        return categoriesMapper.updateByPrimaryKeySelective(categories);
    }
}
