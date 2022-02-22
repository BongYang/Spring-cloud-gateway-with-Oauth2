package com.jwon.oauth2.business.user.service;

import com.jwon.oauth2.business.user.domain.entity.OAuth2UserInfo;
import com.jwon.oauth2.business.user.domain.provider.OAuth2UserInfoFactory;
import com.jwon.oauth2.business.user.repository.OAuth2UserInfoRepository;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcReactiveOAuth2UserService;
import org.springframework.security.oauth2.client.oidc.userinfo.OidcUserRequest;
import org.springframework.security.oauth2.client.userinfo.ReactiveOAuth2UserService;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
public class CustomReactiveOAuth2OidcUserService implements ReactiveOAuth2UserService<OidcUserRequest, OidcUser> {

    private final OAuth2UserInfoRepository oAuth2UserInfoRepository;

    public CustomReactiveOAuth2OidcUserService(OAuth2UserInfoRepository oAuth2UserInfoRepository) {
        this.oAuth2UserInfoRepository = oAuth2UserInfoRepository;
    }

    @Override
    public Mono<OidcUser> loadUser(OidcUserRequest userRequest) throws OAuth2AuthenticationException {
        final OidcReactiveOAuth2UserService delegate = new OidcReactiveOAuth2UserService();
        final String clientRegistrationId = userRequest.getClientRegistration().getRegistrationId();

        Mono<OidcUser> oAuth2User = delegate.loadUser(userRequest);

        return oAuth2User.flatMap(e -> {
            OAuth2UserInfo oAuth2UserInfo = OAuth2UserInfoFactory.getOAuth2UserInfo(clientRegistrationId, e.getAttributes());
            return oAuth2UserInfoRepository
                    .findByEmail(oAuth2UserInfo.getEmail())
                    .switchIfEmpty(Mono.defer(() -> oAuth2UserInfoRepository.save(oAuth2UserInfo)));
        });
    }
}
