package com.example.springbootcloudgateway.user.web.controller;

import com.example.springbootcloudgateway.user.domain.entity.OAuth2UserInfo;
import com.example.springbootcloudgateway.user.domain.provider.CurrentUser;
import com.example.springbootcloudgateway.user.service.OAuth2UserInfoService;
import com.example.springbootcloudgateway.user.web.dto.OAuth2UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import javax.validation.Valid;

@RestController
@RequestMapping("/")
public class OAuth2UserInfoController {

    @Autowired
    private OAuth2UserInfoService oAuth2UserInfoService;

//    public OAuth2UserInfoController(OAuth2UserInfoService oAuth2UserInfoService) {
//        this.oAuth2UserInfoService = oAuth2UserInfoService;
//    }

    @GetMapping
    public Mono<OAuth2UserInfoDto> getUserInfo(@CurrentUser OAuth2UserInfo oAuth2UserInfo) {

        return Mono.just(OAuth2UserInfoDto.builder()
                .name(oAuth2UserInfo.getName())
                .nickName(oAuth2UserInfo.getNickName())
                .email(oAuth2UserInfo.getEmail())
                .image(oAuth2UserInfo.getImage())
                .authProvider(oAuth2UserInfo.getAuthProvider())
                .role(oAuth2UserInfo.getRole())
                .build());
    }
    @GetMapping("aa")
    public Mono<String> getInfo() {
        return ReactiveSecurityContextHolder.getContext()
                .switchIfEmpty(Mono.error(new IllegalStateException("ReactiveSecurityContext is empty")))
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .cast(OAuth2UserInfo.class)
                .flatMap(s -> Mono.just("Hi " + s.getEmail()))
                .doOnNext(System.out::println)
                .doOnError(Throwable::printStackTrace)
                .doOnSuccess(s -> System.out.println("completed without value: " + s));
    }

    @PutMapping
    public Mono<OAuth2UserInfoDto> updateUserInfo(@Valid @RequestBody OAuth2UserInfoDto.UpdateDto updateDto, @CurrentUser OAuth2UserInfo oAuth2UserInfo) {
        return oAuth2UserInfoService.updateUserInfo(updateDto, oAuth2UserInfo);
    }

}