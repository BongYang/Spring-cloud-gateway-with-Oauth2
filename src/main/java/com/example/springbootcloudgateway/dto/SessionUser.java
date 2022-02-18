package com.example.springbootcloudgateway.dto;

import com.example.springbootcloudgateway.entity.User;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {

    private String userId;

    private String name;

    private String email;

    private String picture;

    public SessionUser(User user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.email = user.getEmail();
        this.picture = user.getPicture();
    }
}