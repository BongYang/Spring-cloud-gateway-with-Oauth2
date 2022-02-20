package com.example.springbootcloudgateway.user.repository;

import com.example.springbootcloudgateway.user.domain.entity.User;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import reactor.core.publisher.Mono;

public interface OAuth2UserInfoRepository extends ReactiveMongoRepository<User, String> {
    Mono<User> findByEmail(String email);
}