package com.hmall.gateway.filters;

import java.util.List;

import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;

import com.hmall.common.exception.UnauthorizedException;
import com.hmall.gateway.config.AuthProperties;
import com.hmall.gateway.utils.JwtTool;

import cn.hutool.core.text.AntPathMatcher;
import lombok.RequiredArgsConstructor;
import reactor.core.publisher.Mono;

@Component
@RequiredArgsConstructor
public class AuthGlobalFilter implements GlobalFilter, Ordered {
    private final AuthProperties authProperties;
    private final JwtTool jwtTool;
    private final AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public int getOrder() {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        // 获取用户信息
        ServerHttpRequest request = exchange.getRequest();
        // 判断是否需要登录拦截
        if (isExclued(request.getPath().toString()))
            return chain.filter(exchange);
        // 获取token
        String token = null;
        List<String> headers = request.getHeaders().get("authorization");
        if (headers != null && !headers.isEmpty()) {
            token = headers.get(0);
        }
        // 校验并解析token
        Long userId = null;
        try {
            userId = jwtTool.parseToken(token);
        } catch (UnauthorizedException e) {
            // 拦截 设置响应状态码
            ServerHttpResponse response = exchange.getResponse();
            response.setStatusCode(HttpStatus.UNAUTHORIZED);
            return response.setComplete(); // 从这里中止 以后拦截器不会响应了
        }
        // c传递用户信息
        System.out.println("userId=" + userId);
        String userInfo = userId.toString();
        ServerWebExchange swe = exchange.mutate() // 对下游处理
                .request(builder -> builder.header("user-info", userInfo))
                .build();
        // 放行
        return chain.filter(swe);
    }

    private boolean isExclued(String path) {
        for (String pathParttern : authProperties.getExcludePaths()) {
            if (antPathMatcher.match(pathParttern, path))
                return true;
        }
        return false;
    }

}
