package com.dearlavion.gateway.config;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class GatewayConfig {

    @Bean
    public KeyResolver userOrIpKeyResolver() {
        return exchange -> {

            String token = exchange.getRequest()
                    .getHeaders()
                    .getFirst("Authorization");

            if (token != null && token.startsWith("Bearer ")) {
                String user = extractUser(token);
                return Mono.just(user);
            }

            // 🔥 fallback to IP
            String ip = exchange.getRequest()
                    .getRemoteAddress()
                    .getAddress()
                    .getHostAddress();

            return Mono.just(ip);
        };
    }

    private String extractUser(String token) {
        // TODO: decode JWT properly
        return token; // placeholder
    }
}
