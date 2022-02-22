package com.jwon.oauth2.business.user.domain.entity;


import com.jwon.oauth2.business.user.domain.provider.AuthProvider;

import java.util.Map;

public class GoogleOAuth2UserInfo extends OAuth2UserInfo {

    public GoogleOAuth2UserInfo(Map<String, Object> attributes) {
        super(attributes);
    }

    @Override
    protected AuthProvider getAuthProviderEnum() {
        return AuthProvider.GOOGLE;
    }

    @Override
    protected void setAttribute() {
        this.name = String.valueOf(String.valueOf(attributes.get("sub")));
        this.nickName = String.valueOf(attributes.get("name"));
        this.email = String.valueOf(attributes.get("email"));
        this.image = String.valueOf(attributes.get("picture"));
    }
}
