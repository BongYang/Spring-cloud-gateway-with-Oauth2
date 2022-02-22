package com.example.springbootcloudgateway.filter;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilter;
import org.springframework.cloud.gateway.filter.factory.AbstractGatewayFilterFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Slf4j
@Component
public class MyGlobalFilter extends AbstractGatewayFilterFactory<MyGlobalFilter.Config> {
//    private static final Logger logger = LogManager.getLogger(MyGlobalFilter.class);

    public MyGlobalFilter() {
        super(Config.class);
    }

    @Override
    public GatewayFilter apply(Config config) {
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