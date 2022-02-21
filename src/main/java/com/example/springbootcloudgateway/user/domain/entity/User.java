package com.example.springbootcloudgateway.user.domain.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter //lombok , 모든 field 값의 getter 매소드를 자동 생성
@NoArgsConstructor // lombok , 기본 생성자 자동추가
@Document(collection = "user") //DB에 저장될 document의 이름은 @Documemt 애노테이션을 통하여 지정
public class User {

    @Id
    private String userId;

    private String name;
    private String email;
    private String picture;
    private String role;

    @Builder
    public User(String name, String email, String picture, String role) {
        this.name = name;
        this.email = email;
        this.picture = picture;
        this.role = role;
    }

    public User update(String name, String picture) {
        this.name = name;
        this.picture = picture;
        return this; }

    @Override
    public String toString() {
        return "User{" +
                "userId='" + userId + '\'' +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", picture='" + picture + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}