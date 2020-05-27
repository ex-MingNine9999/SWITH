package com.ggomak.springboot2.domain;

import com.ggomak.springboot2.domain.enums.RoleType;
import com.ggomak.springboot2.domain.enums.SocialType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@NoArgsConstructor
@Entity
@Table
public class User implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String name;

    @Column
    //@JsonIgnore
    private String password;

    @Column
    private String email;

    @Enumerated(EnumType.STRING) // enum 타입을 테이블에 넣을경우 default가 int라 String으로 변경
    @Column(nullable = false)
    private SocialType socialType;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private RoleType roleType;

    @Column
    private String sessionAddress;

    @Column
    private Long sessionContent;

    @Builder
    public User(String name, String password, String email, SocialType socialType, RoleType roleType) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.socialType = socialType;
        this.roleType = roleType;
    }

    public User update(String name, SocialType socialType) {    // 유저정보가 업데이트 되었을 때(소셜 로그인) 정보 갱신 하는 메소드
        this.name = name;
        this.socialType = socialType;

        return this;
    }

    public void setOriginUser(String password, SocialType socialType, RoleType roleType){   // 자체로그인시 유저정보 세팅
        this.password = password;
        this.socialType = socialType;
        this.roleType = roleType;
    }

    public void setSocialType(SocialType socialType){
        this.socialType = socialType;
    }

    public String getRoleKey() {
        return this.roleType.getKey();
    }

    public void setSessionAddress(String sessionAddress){
        this.sessionAddress = sessionAddress;
    }

    public void setSessionContent(Long sessionContent){
        this.sessionContent = sessionContent;
    }
}
