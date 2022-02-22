# springboot-oauth2
springboot2.0 webflux 기반의 OAuth2.0 인증 예제 입니다.

#### 스프링부트 설정 만으로 OAuth client 구현 가능
```
spring:
  profiles: dev
  security:
    oauth2:
      client:
        registration:
          google:
            client-id: your_client_id
            client-secret: your_client_secret
```

#### 사용자 정보를 저장하여 활용할 경우 인증/인가 시점에 저장 필요
- OIDC : ReactiveOAuth2UserService<OidcUserRequest, OidcUser> 상속 받아 구현 
- OAUTH2.0 : ReactiveOAuth2UserService<OAuth2UserRequest, OAuth2User> 상속 받아 구현

#### OAuth와 OIDC 차이 참고
[OAuth와 춤을 - NAVER D2 - 네이버](https://d2.naver.com/helloworld/24942)

