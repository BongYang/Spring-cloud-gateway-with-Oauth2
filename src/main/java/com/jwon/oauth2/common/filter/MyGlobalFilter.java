package com.jwon.oauth2.common.filter;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.*;

@Slf4j
@Component
public class MyGlobalFilter extends AbstractGatewayFilterFactory<MyGlobalFilter.Config> {
//    private static final Logger logger = LogManager.getLogger(MyGlobalFilter.class);

    public MyGlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
//        Optional<String> user_email = ReactiveSecurityContextHolder.getContext()
//                .onErrorReturn(new SecurityContext() {
//                    @Override
//                    public Authentication getAuthentication() { return null; }
//                    @Override
//                    public void setAuthentication(Authentication authentication) {}
//                })
//                .map(SecurityContext::getAuthentication)
//                .map(Authentication::getPrincipal)
//                .cast(OAuth2UserInfo.class)
//                .flatMap(s -> Mono.just(s.getEmail())).blockOptional();
//        log.info(user_email.orElse("Empty"));
        return ((exchange, chain) -> {
//            exchange.getRequest().getHeaders().add("user_email", user_email);
            log.info("GlobalFilter baseMessage>>>>>>" + config.getBaseMessage());
            if (config.isPreLogger()) {
                log.info("GlobalFilter Start>>>>>>" + exchange.getRequest());
            }
            return chain.filter(exchange).then(Mono.fromRunnable(()->{
                if (config.isPostLogger()) {
                    log.info("GlobalFilter End>>>>>>" + exchange.getResponse());
                }
            }));
        });
    }

    @Data
    public static class Config {
        private String baseMessage;
        private boolean preLogger;
        private boolean postLogger;
    }
}