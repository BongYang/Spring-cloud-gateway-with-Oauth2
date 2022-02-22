package com.jwon.oauth2.business.user.web.controller;

import com.jwon.oauth2.business.user.domain.entity.OAuth2UserInfo;
import com.jwon.oauth2.business.user.domain.provider.CurrentUser;
import com.jwon.oauth2.business.user.service.OAuth2UserInfoService;
import com.jwon.oauth2.business.user.web.dto.OAuth2UserInfoDto;
import com.sun.tools.javac.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.ReactiveSecurityContextHolder;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import javax.validation.Valid;
import java.security.Principal;
import java.util.ArrayList;

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
        String result = "empty ";
//        Authentication auth = ReactiveSecurityContextHolder.getContext();
        return ReactiveSecurityContextHolder.getContext()
                .switchIfEmpty(Mono.error(new IllegalStateException("ReactiveSecurityContext is empty")))
                .map(SecurityContext::getAuthentication)
                .map(Authentication::getPrincipal)
                .cast(OAuth2UserInfo.class)
                .flatMap(s -> Mono.just("Hi " + s.getEmail()))
                .doOnNext(System.out::println)
                .doOnError(Throwable::printStackTrace)
                .doOnSuccess(s -> System.out.println("completed without value: " + s));

//        Authentication auth = ReactiveSecurityContextHolder.getContext()
//                .map(context -> context.getAuthentication()).block();
//
//        if (auth == null){
//            return Mono.just("Auth is null");
//        }
//        Object principal = auth.getPrincipal();
//        if (principal == null){
//            return Mono.just("principal is null");
//        }
//        OAuth2UserInfo userInfo = (OAuth2UserInfo) principal;
//        return Mono.just(userInfo.getEmail());

//        if(auth != null){
//            result = result + auth.getName();
//            System.out.println(auth.getName());
//            Object principal = auth.getPrincipal();
//            if(principal != null){
//                OAuth2UserInfo userDetails = (OAuth2UserInfo) principal;
//                result = result + " " + userDetails.getEmail();
//                System.out.println(userDetails.getEmail());
//            }
//        }

//        return Mono.just(result);
    }

    @PutMapping
    public Mono<OAuth2UserInfoDto> updateUserInfo(@Valid @RequestBody OAuth2UserInfoDto.UpdateDto updateDto, @CurrentUser OAuth2UserInfo oAuth2UserInfo) {
        return oAuth2UserInfoService.updateUserInfo(updateDto, oAuth2UserInfo);
    }

}