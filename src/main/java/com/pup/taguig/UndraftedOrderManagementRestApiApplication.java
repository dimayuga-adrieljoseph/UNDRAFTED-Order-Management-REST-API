package com.pup.taguig;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.pup.taguig.repository")
public class UndraftedOrderManagementRestApiApplication {
    public static void main(String[] args) {
        SpringApplication.run(UndraftedOrderManagementRestApiApplication.class, args);
    }
}