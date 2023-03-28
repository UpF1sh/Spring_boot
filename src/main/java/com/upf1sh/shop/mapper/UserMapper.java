package com.upf1sh.shop.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.upf1sh.shop.bean.User;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper extends BaseMapper<User> {
    @Select("select * from t_user where username=#{username}")
    User findByUN(String username);

    @Select("select * from t_user where id=#{id}")
    User selectbyid(int id);



}
