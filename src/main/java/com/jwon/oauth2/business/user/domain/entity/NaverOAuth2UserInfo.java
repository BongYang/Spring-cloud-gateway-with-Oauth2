package com.jwon.oauth2.business.user.domain.entity;

import com.jwon.oauth2.business.user.domain.provider.AuthProvider;

import java.util.Map;

public class NaverOAuth2UserInfo extends OAuth2UserInfo {

    public NaverOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    protected AuthProvider getAuthProviderEnum() {
        return AuthProvider.NAVER;
    }

    @Override
    protected void setAttribute() {
        Map<String, Object> response = (Map<String, Object>) attributes.get("response");

        this.name = String.valueOf(response.get("id"));
        this.nickName = String.valueOf(response.get("name"));
        this.email = String.valueOf(response.get("email"));
        this.image = String.valueOf(response.get("profile_image"));
    }
}
