//package com.example.springbootcloudgateway.configuration.jw.oauth;
//
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.oauth2.client.registration.ClientRegistration;
//import org.springframework.security.oauth2.client.registration.InMemoryReactiveClientRegistrationRepository;
//import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
//
//@Configuration
//public class OAuth2LoginConfig {
//
//    @Bean
//    public ReactiveClientRegistrationRepository clientRegistrationRepository(){
//        return new InMemoryReactiveClientRegistrationRepository(this.googleClientRegistration());
//    }
//
//    private ClientRegistration googleClientRegistration(){
//        return ClientRegistration.withRegistrationId("google")
//                .clientId()
//                .clientSecret()
//                .clientAuthenticationMethod()
//                .authorizationGrantType()
//                .redirectUri()
//                .scope()
//                .authorizationUri()
//                .tokenUri()
//                .userInfoUri()
//                .userNameAttributeName()
//                .jwkSetUri()
//                .clientName()
//                .build();
//    }
//}
