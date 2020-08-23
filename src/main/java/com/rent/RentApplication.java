package com.rent;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.rent.dao")
public class RentApplication {
    public static void main(String[] args) {
        SpringApplication.run(RentApplication.class, args);
    }

}
