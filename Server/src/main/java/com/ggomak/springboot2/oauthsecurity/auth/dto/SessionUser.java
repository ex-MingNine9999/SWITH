package com.ggomak.springboot2.oauthsecurity.auth.dto;

import com.ggomak.springboot2.domain.User;
import com.ggomak.springboot2.domain.enums.SocialType;
import lombok.Getter;

import java.io.Serializable;

@Getter
public class SessionUser implements Serializable {
    private String name;
    private String email;
    private SocialType socialType;

    public SessionUser(User user) {
        this.name = user.getName();
        this.email = user.getEmail();
        this.socialType = user.getSocialType();
    }
}
