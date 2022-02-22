package com.example.springbootcloudgateway.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.buffer.DataBuffer;
import org.springframework.http.server.reactive.ServerHttpRequest;
import org.springframework.http.server.reactive.ServerHttpRequestDecorator;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.ServerWebExchangeDecorator;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.channels.Channels;

@Slf4j
@Component
public class ReactiveLoggingFilter implements WebFilter {

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        if (exchange.getRequest().getPath().value().startsWith("/monitor")) return chain.filter(exchange);
        return chain.filter(decorate(exchange));
    }

    private ServerWebExchange decorate(ServerWebExchange exchange) {
        log.info("[{}] Request: method={}, path={}, param = {}", exchange.getRequest().getId(), exchange.getRequest().getMethod(), exchange.getRequest().getPath(), exchange.getRequest().getQueryParams());

        return new ServerWebExchangeDecorator(exchange) {
            @Override
            public ServerHttpRequest getRequest() {
                return new ServerHttpRequestDecorator(exchange.getRequest()) {
                    @Override
                    public Flux<DataBuffer> getBody() {
                        return super.getBody().doOnNext(dataBuffer -> {
                            try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();) {
                                Channels.newChannel(byteArrayOutputStream).write(dataBuffer.asByteBuffer().asReadOnlyBuffer());
                                log.info("[{}] Request: payload = {}", exchange.getRequest().getId(), byteArrayOutputStream.toString().replaceAll(System.getProperty("line.separator"), " "));
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        });
                    }
                };
            }
        };
    }
}
