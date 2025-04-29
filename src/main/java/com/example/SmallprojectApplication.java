package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@SpringBootApplication()
@EnableAspectJAutoProxy
@MapperScan("com.example.board.mapper")
public class SmallprojectApplication {
    public static void main(String[] args) {
        SpringApplication.run(SmallprojectApplication.class, args);
    }
}
