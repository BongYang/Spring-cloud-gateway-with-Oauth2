package com.example.springbootcloudgateway.user.domain.provider;

import com.example.springbootcloudgateway.user.domain.entity.GoogleOAuth2UserInfo;
import com.example.springbootcloudgateway.user.domain.entity.KakaoOAuth2UserInfo;
import com.example.springbootcloudgateway.user.domain.entity.NaverOAuth2UserInfo;
import com.example.springbootcloudgateway.user.domain.entity.OAuth2UserInfo;

import java.util.Map;

public class OAuth2UserInfoFactory {
    public static OAuth2UserInfo getOAuth2UserInfo(String registrationId, Map<String, Object> attributes) {
        if (registrationId.equalsIgnoreCase(AuthProvider.GOOGLE.getProviderType())) {
            return new GoogleOAuth2UserInfo(attributes);
        }
        else if (registrationId.equalsIgnoreCase(AuthProvider.KAKAO.getProviderType())) {
            return new KakaoOAuth2UserInfo(attributes);
        }
        else if (registrationId.equalsIgnoreCase(AuthProvider.NAVER.getProviderType())) {
            return new NaverOAuth2UserInfo(attributes);
        } else {
            throw new RuntimeException("unsupported provider");
        }
    }
}
