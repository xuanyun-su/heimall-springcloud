package com.hmall.trade;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

import com.hmall.api.config.DefaultFeignConfig;

@EnableFeignClients(basePackages = "com.hmall.api.client", defaultConfiguration = DefaultFeignConfig.class) // 开启OpenFeign
@SpringBootApplication
public class TradeService {
    public static void main(String[] args) {
       SpringApplication.run(TradeService.class, args);
    }
}