package com.hmall.cart;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

import com.hmall.api.config.DefaultFeignConfig;


@EnableFeignClients(basePackages = "com.hmall.api.client",defaultConfiguration = DefaultFeignConfig.class) // 开启OpenFeign
@SpringBootApplication
// @MapperScan("com.hmall.cart.mapper")
public class CartApplication {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    public static void main (String[] args){
        SpringApplication.run(CartApplication.class,args);
    }
}
