package com.example.springbootcloudgateway.configuration.oauth;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableReactiveMethodSecurity;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.web.server.SecurityWebFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableWebFluxSecurity
@EnableReactiveMethodSecurity
public class SecurityConfig {

    @Bean
    SecurityWebFilterChain springSecurityFilterChain(ServerHttpSecurity http) throws Exception {
        http
                .authorizeExchange()
                .pathMatchers("/resource").hasAuthority("SCOPE_resource.read")
                .anyExchange().authenticated()
                .and()
                .oauth2ResourceServer()
                .jwt();
        return http.build();
    }

//    @Bean
//    public SecurityWebFilterChain securityWebFilterChain(final ServerHttpSecurity serverHttpSecurity) throws Exception {
//
////        return serverHttpSecurity
////                .csrf().disable()
////                .httpBasic().disable()
////                .formLogin().disable()
////                .oauth2Login(withDefaults())
////                .logout()
////                .and()
////                .authorizeExchange()
////                .pathMatchers("/").permitAll()
////                .anyExchange().permitAll()
////                .and()
////                .build()
////                ;
//        return serverHttpSecurity.authorizeExchange()
//                .pathMatchers("/").permitAll()
//                .anyExchange().authenticated()
//                .and().httpBasic()
//                .and().formLogin().disable().csrf().disable()
//                .build();
//    }


}
