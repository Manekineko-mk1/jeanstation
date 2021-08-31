package com.stackroute.apigateway.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.stackroute.apigateway.filters.JwtAuthenticationFilter;

@Configuration
public class GatewayConfig {

    @Autowired
    private JwtAuthenticationFilter filter;

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder) {
        return builder.routes()
//                .route("auth", r -> r.path("/auth/**").filters(f -> f.filter(filter)).uri("lb://auth"))
                .route("product", r -> r.path("/api/v1/product/**").filters(f -> f.filter(filter)).uri("lb://product"))
                .route("user", r -> r.path("/api/v1/user/**").filters(f -> f.filter(filter)).uri("lb://user"))
                .route("order", r -> r.path("/api/v1/order/**").filters(f -> f.filter(filter)).uri("lb://order"))
                .route("cart", r -> r.path("/api/v1/cart/**").filters(f -> f.filter(filter)).uri("lb://cart")).build();
    }

}
