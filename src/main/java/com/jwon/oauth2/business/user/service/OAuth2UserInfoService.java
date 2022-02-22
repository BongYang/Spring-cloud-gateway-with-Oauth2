package com.jwon.oauth2.business.user.service;

import com.jwon.oauth2.business.user.domain.entity.OAuth2UserInfo;
import com.jwon.oauth2.business.user.repository.OAuth2UserInfoRepository;
import com.jwon.oauth2.business.user.web.dto.OAuth2UserInfoDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class OAuth2UserInfoService {

    @Autowired
    private OAuth2UserInfoRepository oAuth2UserInfoRepository;

//    public OAuth2UserInfoService(OAuth2UserInfoRepository oAuth2UserInfoRepository) {
//        this.oAuth2UserInfoRepository = oAuth2UserInfoRepository;
//    }

    public Mono<OAuth2UserInfoDto> updateUserInfo(OAuth2UserInfoDto.UpdateDto updateDto, OAuth2UserInfo oAuth2UserInfo) {
        return oAuth2UserInfoRepository.findById(oAuth2UserInfo.getId().toString())
                .map(document -> document.update(updateDto))
                .flatMap(document -> oAuth2UserInfoRepository.save(document))
                .map(document -> OAuth2UserInfoDto.builder()
                        .name(document.getName())
                        .nickName(document.getNickName())
                        .email(document.getEmail())
                        .image(document.getImage())
                        .authProvider(document.getAuthProvider())
                        .build()
                );
    }
}
