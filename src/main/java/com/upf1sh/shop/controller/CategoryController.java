package com.upf1sh.shop.controller;

import com.upf1sh.shop.bean.Category;
import com.upf1sh.shop.service.CategoryService;
import com.upf1sh.shop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @RequestMapping("/all")
    public Result getAll(){
//      查询所有顶级分类
        List<Category> categories = categoryService.findAll();
        return Result.ok().data("items",categories);
    }

    @RequestMapping("/get")
    public Result getByParentId(Long parentId){
//      根据父ID查询子分类
        List<Category> categories = categoryService.getByParentId(parentId);
        return Result.ok().data("items",categories);
    }

}
