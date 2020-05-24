package com.ggomak.springboot2.dto;

import com.ggomak.springboot2.domain.Board;
import com.ggomak.springboot2.domain.enums.BoardType;

public class BoardTempDto {
    private String title;
    private String subtitle;
    private String content;
    private BoardType boardType;

    public Board toEntity(String title, String subtitle, String content, BoardType boardType) {
        return Board.builder()
                .title(title)
                .subTitle(subtitle)
                .content(content)
                .boardType(boardType)
                .build();
    }
}
