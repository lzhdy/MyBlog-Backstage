package com.lzhdy;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author lzhdy
 * @date 2022/11/7
 * @apiNote 博客前台启动类
 */
@SpringBootApplication
@MapperScan("com.lzhdy.mapper")
public class blogApplication {
    public static void main(String[] args) {
        SpringApplication.run(blogApplication.class,args);
    }
}
