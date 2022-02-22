package com.example.springbootcloudgateway.user.web.controller;

import com.example.springbootcloudgateway.user.service.OAuth2UserInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@Secured("ROLE_ADMIN")
@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private OAuth2UserInfoService oAuth2UserInfoService;

    @GetMapping
    public Mono<String> admin(){
        return Mono.just("admin page");
    }

}
