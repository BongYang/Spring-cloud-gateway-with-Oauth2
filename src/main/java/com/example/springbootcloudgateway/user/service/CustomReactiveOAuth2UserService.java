package com.example.springbootcloudgateway.user.service;

import com.example.springbootcloudgateway.user.dto.OAuthAttributes;
import com.example.springbootcloudgateway.user.domain.entity.User;
import com.example.springbootcloudgateway.user.domain.entity.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.*;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.Collections;

@RequiredArgsConstructor
@Service
public class CustomReactiveOAuth2UserService implements ReactiveOAuth2UserService<OAuth2UserRequest, OAuth2User> {

    private final UserRepository userRepository;

    @Override
    public Mono<OAuth2User> loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {

        DefaultReactiveOAuth2UserService delegate = new DefaultReactiveOAuth2UserService();

        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        Mono<OAuth2User> oAuth2User = delegate.loadUser(userRequest);

        String userNameAttributeName = userRequest.getClientRegistration()
                .getProviderDetails().getUserInfoEndpoint().getUserNameAttributeName();

        System.out.println("CustomReactiveOAuth2UserService 호출됨");
        return oAuth2User.flatMap( e -> {
             OAuthAttributes attributes = OAuthAttributes.of(registrationId,userNameAttributeName,e.getAttributes());
             saveOrUpdate(attributes);
             return Mono.just(new DefaultOAuth2User(
                     Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")),
                     attributes.getAttributes(),
                     attributes.getNameAttributeKey())
             );
        });
    }

    private User saveOrUpdate(OAuthAttributes attributes) {
        User user = userRepository.findByEmail(attributes.getEmail())
                .map(entity -> entity.update(attributes.getName(),attributes.getPicture()))
                .orElse(attributes.toEntity());
        return userRepository.save(user);
    }
}
