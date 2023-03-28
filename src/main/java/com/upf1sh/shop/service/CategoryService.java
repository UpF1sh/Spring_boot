package com.upf1sh.shop.service;

import com.upf1sh.shop.bean.Category;
import com.upf1sh.shop.mapper.CategoryMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    public List<Category> findAll() {
        return categoryMapper.findSAll();
    }
    public List<Category> getByParentId(Long parentId) {
        return categoryMapper.getByParentId(parentId);
    }
}
