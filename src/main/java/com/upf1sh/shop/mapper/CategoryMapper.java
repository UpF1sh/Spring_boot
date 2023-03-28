package com.upf1sh.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.upf1sh.shop.bean.Category;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface CategoryMapper extends BaseMapper<Category> {


    @Select("select * from category where parent_id = 0")
    List<Category> findSAll();

    @Select("select * from category where parent_id = #{parentId}")
    List<Category> getByParentId(Long parentId);
}
