package com.example.springbootcloudgateway.user.web.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class GatewayController {
    @GetMapping("/")
    public Mono<String> test(){
        return Mono.just("this is GateWay");
    }

    @GetMapping("/api/hello")
    public Mono<String> hello(){
        return Mono.just("hello");
    }
}
