package com.hmall.users;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.hmall.api.config.DefaultFeignConfig;

@SpringBootApplication
@EnableFeignClients(basePackages = "com.hmall.api.client",defaultConfiguration = DefaultFeignConfig.class) // 开启OpenFeign
public class UserService {
    public static void main(String[] args) {
        SpringApplication.run(UserService.class, args);
    }
}