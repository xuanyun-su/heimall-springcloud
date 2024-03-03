package com.hmall.pay;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.hmall.api.config.DefaultFeignConfig;

@EnableFeignClients(basePackages = "com.hmall.api.client", defaultConfiguration = DefaultFeignConfig.class) // 开启OpenFeign
@SpringBootApplication
public class PayService {
    public static void main(String[] args) {
        SpringApplication.run(PayService.class, args);
    }
}