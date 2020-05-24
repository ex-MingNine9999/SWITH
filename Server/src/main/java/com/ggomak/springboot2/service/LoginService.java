package com.ggomak.springboot2.service;

import com.ggomak.springboot2.domain.User;
import com.ggomak.springboot2.domain.enums.RoleType;
import com.ggomak.springboot2.domain.enums.SocialType;
import com.ggomak.springboot2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class LoginService {

    private final UserRepository userRepository;
    private BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);

    @Transactional
    public Long signUp(User requestDto) {

        // 회원가입 유저정보 설정(비밀번호 암호화, Social type, Role type)
        requestDto.setOriginUser(passwordEncoder.encode(requestDto.getPassword()), SocialType.ORIGIN, RoleType.USER);

        return userRepository.save(requestDto).getId();
    }
}
