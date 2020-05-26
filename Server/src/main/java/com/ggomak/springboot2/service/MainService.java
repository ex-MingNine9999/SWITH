package com.ggomak.springboot2.service;

import com.ggomak.springboot2.domain.User;
import com.ggomak.springboot2.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.springboot2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MainService {

    private final UserRepository userRepository;

    public void regist(String sessionAddress, SessionUser user){

        Optional<User> tempuser = userRepository.findByEmailAndSocialType(user.getEmail(), user.getSocialType());
        tempuser.get().setSessionAddress(sessionAddress);

        userRepository.save(tempuser.get());
    }
}
