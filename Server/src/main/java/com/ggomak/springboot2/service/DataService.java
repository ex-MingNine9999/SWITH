package com.ggomak.springboot2.service;

import com.ggomak.springboot2.domain.Content;
import com.ggomak.springboot2.domain.Data;
import com.ggomak.springboot2.domain.User;
import com.ggomak.springboot2.domain.enums.SocialType;
import com.ggomak.springboot2.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.springboot2.repository.ContentRepository;
import com.ggomak.springboot2.repository.DataRepository;
import com.ggomak.springboot2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DataService {

    private final DataRepository dataRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    public void save(SessionUser sessionUser, Data data){

        Optional<User> user = userRepository.findByEmailAndSocialType(sessionUser.getEmail(), sessionUser.getSocialType());
        Content content = contentRepository.findByContentNumber(data.getContent().getContentNumber());

        dataRepository.save(Data.builder()
                .user(user.get())
                .content(content)
                .concentrateData(data.getConcentrateData())
                .build());
    }

    public String load(SessionUser sessionUser, Long contentNumber){

        Optional<User> user = userRepository.findByEmailAndSocialType("test@gmail.com", SocialType.ORIGIN);
        Content content = contentRepository.findByContentNumber(contentNumber);

        return dataRepository.findByUserAndContent(user.get(), content).getConcentrateData();
    }
}
