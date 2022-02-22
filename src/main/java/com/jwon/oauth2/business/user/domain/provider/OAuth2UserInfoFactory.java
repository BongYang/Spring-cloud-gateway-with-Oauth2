package com.jwon.oauth2.business.user.domain.provider;

import com.jwon.oauth2.business.user.domain.entity.GoogleOAuth2UserInfo;
import com.jwon.oauth2.business.user.domain.entity.KakaoOAuth2UserInfo;
import com.jwon.oauth2.business.user.domain.entity.NaverOAuth2UserInfo;
import com.jwon.oauth2.business.user.domain.entity.OAuth2UserInfo;

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
