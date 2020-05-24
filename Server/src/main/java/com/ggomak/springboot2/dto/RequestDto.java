package com.ggomak.springboot2.dto;

import com.ggomak.springboot2.domain.Board;
import com.ggomak.springboot2.domain.enums.BoardType;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
public class RequestDto {
    private String title;
    private BoardType boardType;
    private String subTitle;
    private String content;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .boardType(boardType)
                .subTitle(subTitle)
                .content(content)
                .build();
    }

}
