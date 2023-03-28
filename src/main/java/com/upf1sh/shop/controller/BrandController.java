package com.upf1sh.shop.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.upf1sh.shop.bean.Brand;
import com.upf1sh.shop.service.BrandService;
import com.upf1sh.shop.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/brand")
@CrossOrigin
public class BrandController {

    @Autowired
    private BrandService brandService;

    @RequestMapping("/all")
    public Result getAll(@RequestParam(defaultValue = "1") int pageNum) {

//      分页对象，传入当前页码及每页的数量
        Page<Brand> page = new Page(pageNum, 10);

        Page<Brand> brands = brandService.findAll(page);
        System.out.println(brands);
        return Result.ok().data("items", brands);
    }

    @RequestMapping("/get")
    public Result getById(Long id) {
        Brand brand = brandService.findById(id);
        return Result.ok().data("brand", brand);
    }

    @RequestMapping("/getAll")
    public Result getAllBrand() {
        List<Brand> brands = brandService.findAllBrand();
        return Result.ok().data("brands", brands);
    }

    @RequestMapping("/add")
    public Result add(Brand brand, Long categoryId) {
        int r = brandService.add(brand, categoryId);
        return Result.ok();
    }

    @RequestMapping("/edit")
    public Result edit(Brand brand) {
        int r = brandService.edit(brand);
        return Result.ok();
    }

    //  接收上传的品牌图片
    @RequestMapping("/upload")
    public String upload(MultipartFile img) {
//      1. 取出文件原始名称
        String originalFilename = img.getOriginalFilename();
//      2. 为了防止文件名称重复导致覆盖，给每个文件定义一个唯一的名称
        String newFileName = UUID.randomUUID().toString().replace("-", "") + originalFilename;
//      3. 获取程序运行目录
//        String dirPath = System.getProperty("user.dir");
        String dirPath = ("/data");
//      4. 拼接文件存储路径，最终存储到项目的upload目录下
        String path = "/" + "upload" + "/" + newFileName;
        File destFile = new File(dirPath + path);
        System.out.println(path);
//      5. 如果upload目录不存在则创建
        if (!destFile.getParentFile().exists()) {
            destFile.getParentFile().mkdirs();
        }
        try {
//          6. 将前端传来的文件存储到目标路径
            img.transferTo(destFile);
            // 将相对路径返回给前端，用于显示图片
//          /upload/xxxxxxx.jpg
            System.out.println("final path" + path);
            return path;
        } catch (IOException e) {

            return null;
        }
    }

    @RequestMapping("/delete")
    public Result delete(Long id) {
        System.out.println("id" + id);
        int del = brandService.delete(id);
        if (del > 0)
            return Result.ok();
        else
            return Result.error();
    }

    @PostMapping("/del")
    public String test2(int id) {
        System.out.println("test");
        return "ok";
    }

}
