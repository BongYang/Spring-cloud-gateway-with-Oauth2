package com.jwon.oauth2.business.user.web.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class IndexController {

    @GetMapping("/user")
    public Mono<String> index() {
        return Mono.just("only user can access");
    }
}