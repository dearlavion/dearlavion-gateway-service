package com.dearlavion.gateway.resolver;

import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import reactor.core.publisher.Mono;

@Configuration
public class UserKeyResolver {

    @Bean
    public KeyResolver userKeyResolverBean() {
        return exchange -> {

            String token = exchange.getRequest()
                    .getHeaders()
                    .getFirst("Authorization");

            if (token == null) {
                return Mono.just("anonymous");
            }

            // 🔥 Ideally decode JWT and extract username
            String user = extractUsername(token);

            return Mono.just(user);
        };
    }

    private String extractUsername(String token) {
        // TODO: decode JWT properly
        return token; // placeholder
    }
}
