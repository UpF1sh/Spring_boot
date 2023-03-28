package com.upf1sh.shop;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
import com.upf1sh.shop.utils.L_Code;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.upf1sh.shop.mapper")
public class ShopApplication {
    public static void main(String[] args) {

        SpringApplication.run(ShopApplication.class, args);
    }

}
