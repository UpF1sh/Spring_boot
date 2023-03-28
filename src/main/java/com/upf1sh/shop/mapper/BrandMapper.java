package com.upf1sh.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.upf1sh.shop.bean.Brand;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface BrandMapper extends BaseMapper<Brand> {

    @Insert("insert into category_brand values (#{categoryId},#{brandId})")
    public int insertBrandAndCategory(@Param("categoryId") Long categoryId,@Param("brandId") Long brandId);


}
