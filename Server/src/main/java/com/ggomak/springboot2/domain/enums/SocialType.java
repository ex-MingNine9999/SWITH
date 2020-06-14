package com.ggomak.springboot2.domain.enums;

import javax.swing.*;

public enum SocialType {

    FACEBOOK("facebook"),
    GOOGLE("google"),
    KAKAO("kakao"),
    NAVER("naver"),
    ORIGIN("origin");

    private String name;

    SocialType(String name) {
        this.name = name;
    }

    public String getValue() {
        return name;
    }
}
