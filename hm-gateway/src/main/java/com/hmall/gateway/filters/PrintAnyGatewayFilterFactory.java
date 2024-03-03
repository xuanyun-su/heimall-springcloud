package com.hmall.gateway.filters;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.OrderedGatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import lombok.Data;
import reactor.core.publisher.Mono;

// @Component
public class PrintAnyGatewayFilterFactory extends AbstractGatewayFilterFactory<PrintAnyGatewayFilterFactory.Config> {
    @Override
    public GatewayFilter apply(Config config) {
        // return new GatewayFilter() {
        // @Override
        // public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain
        // chain) {
        // // TODO Auto-generated method stub
        // System.out.println("print any filter");
        // return chain.filter(exchange);
        // }
        return new OrderedGatewayFilter(new GatewayFilter() {
            @Override
            public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
                System.out.println("print any filter");
                String a = config.getA();
                String b = config.getB();
                String c = config.getC();
                System.out.println("a=" + a + "b=" + b + "c=" + c);
                return chain.filter(exchange);
            }
        }, 1);
    };

    @Data
    public static class Config {
        private String a;
        private String b;
        private String c;
    }

    @Override
    public List<String> shortcutFieldOrder() {
        return List.of("a", "b", "c");
    }

    public PrintAnyGatewayFilterFactory() {
        super(Config.class);
    }
}
