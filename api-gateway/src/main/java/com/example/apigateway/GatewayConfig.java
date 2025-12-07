package com.example.apigateway;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.cloud.gateway.filter.ratelimit.RedisRateLimiter;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.beans.factory.annotation.Qualifier;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(
                exchange.getRequest()
                        .getRemoteAddress()
                        .getAddress()
                        .getHostAddress()
        );
    }

    @Bean
    @Primary
    public RedisRateLimiter standardRateLimiter() {
        return new RedisRateLimiter(20, 40);
    }

    @Bean
    public RedisRateLimiter strictRateLimiter() {
        return new RedisRateLimiter(5, 10);
    }

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder,
                                           KeyResolver ipKeyResolver,
                                           @Qualifier("standardRateLimiter") RedisRateLimiter standardRateLimiter,
                                           @Qualifier("strictRateLimiter") RedisRateLimiter strictRateLimiter) {

        return builder.routes()

                .route("auth-service", r -> r
                        .path("/auth-service/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .requestRateLimiter(c -> {
                                    c.setKeyResolver(ipKeyResolver);
                                    c.setRateLimiter(standardRateLimiter);
                                })
                        )
                        .uri("lb://auth-service"))

                .route("product-service", r -> r
                        .path("/product-service/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .requestRateLimiter(c -> {
                                    c.setKeyResolver(ipKeyResolver);
                                    c.setRateLimiter(standardRateLimiter);
                                })
                        )
                        .uri("lb://product-service"))

                .route("loan-service", r -> r
                        .path("/loan-service/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .requestRateLimiter(c -> {
                                    c.setKeyResolver(ipKeyResolver);
                                    c.setRateLimiter(standardRateLimiter);
                                })
                        )
                        .uri("lb://loan-service"))

                .route("borrower-service", r -> r
                        .path("/borrower-service/**")
                        .filters(f -> f
                                .circuitBreaker(cb -> cb
                                        .setName("defaultCircuitBreaker")
                                        .setFallbackUri("forward:/fallback/default")
                                )
                                .stripPrefix(1)
                                .requestRateLimiter(c -> {
                                    c.setKeyResolver(ipKeyResolver);
                                    c.setRateLimiter(standardRateLimiter);
                                })
                        )
                        .uri("lb://borrower-service"))

                .route("report-service", r -> r
                        .path("/report-service/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .requestRateLimiter(c -> {
                                    c.setKeyResolver(ipKeyResolver);
                                    c.setRateLimiter(strictRateLimiter);
                                })
                        )
                        .uri("lb://report-service"))

                .route("vendor-service", r -> r
                        .path("/vendor-service/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .requestRateLimiter(c -> {
                                    c.setKeyResolver(ipKeyResolver);
                                    c.setRateLimiter(standardRateLimiter);
                                })
                        )
                        .uri("lb://vendor-service"))

                .route("category-service", r -> r
                        .path("/category-service/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .requestRateLimiter(c -> {
                                    c.setKeyResolver(ipKeyResolver);
                                    c.setRateLimiter(standardRateLimiter);
                                })
                        )
                        .uri("lb://category-service"))

                .route("staff-service", r -> r
                        .path("/staff-service/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .requestRateLimiter(c -> {
                                    c.setKeyResolver(ipKeyResolver);
                                    c.setRateLimiter(standardRateLimiter);
                                })
                        )
                        .uri("lb://staff-service"))

                .route("admin-portal-service", r -> r
                        .path("/admin-portal/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .requestRateLimiter(c -> {
                                    c.setKeyResolver(ipKeyResolver);
                                    c.setRateLimiter(standardRateLimiter);
                                })
                        )
                        .uri("lb://admin-portal-service"))

                .route("staff-portal-service", r -> r
                        .path("/staff-portal/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .requestRateLimiter(c -> {
                                    c.setKeyResolver(ipKeyResolver);
                                    c.setRateLimiter(standardRateLimiter);
                                })
                        )
                        .uri("lb://staff-portal-service"))

                .route("customer-portal-service", r -> r
                        .path("/customer-portal/**")
                        .filters(f -> f
                                .stripPrefix(1)
                                .requestRateLimiter(c -> {
                                    c.setKeyResolver(ipKeyResolver);
                                    c.setRateLimiter(standardRateLimiter);
                                })
                        )
                        .uri("lb://customer-portal-service"))

                .build();
    }
}
