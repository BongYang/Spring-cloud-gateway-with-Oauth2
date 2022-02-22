package com.jwon.oauth2.business.user.web.dto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class OAuth2UserInfoDto {

    private String name;

    private String email;

    private String nickName;

    private String authProvider;

    private String image;

    private String role;

    @Builder
    private OAuth2UserInfoDto(String name, String email, String nickName, String authProvider, String image, String role) {
        this.name = name;
        this.email = email;
        this.nickName = nickName;
        this.authProvider = authProvider;
        this.image = image;
        this.role = role;
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class UpdateDto {
        private String nickName;

        private String image;

        @Builder
        private UpdateDto(final String nickName, final String image) {
            this.nickName = nickName;
            this.image = image;
        }
    }

    @Getter
    @NoArgsConstructor(access = AccessLevel.PRIVATE)
    public static class RoleDto {
        private String role;

        @Builder
        private RoleDto(final String role) {
            this.role = role;
        }
    }
}
