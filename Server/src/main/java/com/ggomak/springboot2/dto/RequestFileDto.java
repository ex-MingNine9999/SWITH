package com.ggomak.springboot2.dto;

import com.ggomak.springboot2.domain.Board;
import com.ggomak.springboot2.domain.Files;
import com.ggomak.springboot2.domain.User;
import lombok.Builder;
import lombok.Getter;

@Getter
public class RequestFileDto {
    User user;
    Files files;

    @Builder
    public Board toEntity(User user, Files files) {
        return Board.builder()
                .user(user)
                .files(files)
                .build();
    }
}
