package com.upf1sh.shop.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.upf1sh.shop.bean.Brand;
import com.upf1sh.shop.mapper.BrandMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    @Autowired
    private BrandMapper brandMapper;

    public Page<Brand> findAll(Page<Brand> page) {

//      调用basemapper的分页查询方法
        return brandMapper.selectPage(page, null);
    }

    public int add(Brand brand, Long categoryId) {
//      插入品牌数据
        int brandResult = brandMapper.insert(brand);
//      插入数据后，mybatisplus会自动将生成的id赋值给brand对象

//      插入分类与品牌关联表数据
        int categoryResult = brandMapper.insertBrandAndCategory(categoryId, brand.getId());
        if (brandResult + categoryResult > 1) {
            return 1;
        }
        return 0;
    }

    public int edit(Brand brand) {
        return brandMapper.updateById(brand);
    }

    public Brand findById(Long id) {
        return brandMapper.selectById(id);
    }

    public List<Brand> findAllBrand() {
        return brandMapper.selectList(null);
    }

    public int delete(Long id) {
        System.out.println("jin2");
        return brandMapper.deleteById(id);
    }

}
