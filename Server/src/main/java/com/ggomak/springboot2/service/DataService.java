package com.ggomak.springboot2.service;

import com.ggomak.springboot2.domain.Content;
import com.ggomak.springboot2.domain.Data;
import com.ggomak.springboot2.domain.User;
import com.ggomak.springboot2.oauthsecurity.auth.dto.SessionUser;
import com.ggomak.springboot2.repository.ContentRepository;
import com.ggomak.springboot2.repository.DataRepository;
import com.ggomak.springboot2.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class DataService {

    private final DataRepository dataRepository;
    private final UserRepository userRepository;
    private final ContentRepository contentRepository;

    public void save(String sessionAddress, String concendata){

        Optional<User> user = userRepository.findBySessionAddress(sessionAddress);

        try {
            Long contentId = user.get().getSessionContent();

            if(contentId == null){
                System.out.println("no session content");
                return;
            }

            Content content = contentRepository.findByContentNumber(contentId);
            Data data = dataRepository.findByUserAndContent(user.get(), content);

            if (data == null) {
                dataRepository.save(Data.builder()
                        .user(user.get())
                        .content(content)
                        .concentrateData(concendata+":")
                        .build());
            } else {
                data.setData(data.getConcentrateData().concat(concendata+":"));
                dataRepository.save(data);
            }
        }
        catch(NoSuchElementException e){
            System.out.println("no session content");
        }

    }

    public String load(SessionUser sessionUser, Long contentNumber){

        Optional<User> user = userRepository.findByEmailAndSocialType(sessionUser.getEmail(), sessionUser.getSocialType());
        Content content = contentRepository.findByContentNumber(contentNumber);

        Data data = dataRepository.findByUserAndContent(user.get(), content);

        if(data == null){
            return null;
        }

        return data.getConcentrateData();
    }
}
