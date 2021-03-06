package com.example.springbootcloudgateway.user.domain.provider;

public enum AuthProvider {

    GOOGLE("GOOGLE"), KAKAO("KAKAO"), NAVER("NAVER");

    private String providerType;

    public String getProviderType() {
        return providerType;
    }

    AuthProvider(final String providerType) {
        this.providerType = providerType;
    }

}
