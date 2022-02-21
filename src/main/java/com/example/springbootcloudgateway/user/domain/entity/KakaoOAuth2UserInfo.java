package com.example.springbootcloudgateway.user.domain.entity;


import com.example.springbootcloudgateway.user.domain.provider.AuthProvider;

import java.util.Map;

public class KakaoOAuth2UserInfo extends OAuth2UserInfo {

    public KakaoOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    protected AuthProvider getAuthProviderEnum() {
        return AuthProvider.KAKAO;
    }

    @Override
    protected void setAttribute() {
        Map<String, Object> properties = (Map<String, Object>) attributes.get("properties");
        this.name = String.valueOf(attributes.get("id"));
        this.nickName = String.valueOf(properties.get("nickname"));
        this.email = String.valueOf(properties.get("email"));
        this.image = String.valueOf(properties.get("profile_image"));
    }
}
