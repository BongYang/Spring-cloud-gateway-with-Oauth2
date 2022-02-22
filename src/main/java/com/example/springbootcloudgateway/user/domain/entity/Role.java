package com.example.springbootcloudgateway.user.domain.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum  Role {

    GUEST("ROLE_GUEST", "손님"),    // 등록 전
    USER("ROLE_USER", "허용된 사용자"), // 등록 후
    ADMIN("ROLE_ADMIN", "관리자");

    private final String key;
    private final String title;
}
