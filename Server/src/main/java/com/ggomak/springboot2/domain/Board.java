package com.ggomak.springboot2.domain;

import com.ggomak.springboot2.domain.enums.BoardType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Getter // get 메소드 생성
@NoArgsConstructor  // 생성자 생성
@Entity // 테이블과 1대1 매칭
public class Board extends BaseEntity implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String subTitle;

    @Column
    private String content;

    @Column
    @Enumerated(EnumType.STRING)
    private BoardType boardType;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private Files files;

    @OneToMany    // 연관 관계 주인 설정 (외래키를 가지지 않는 칼럼)
    private List<Comment> commentList = new ArrayList<>();

    @Builder
    public Board(String title, String subTitle, String content, BoardType boardType, User user, Files files) {
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.boardType = boardType;
        this.user = user;
        this.files = files;
    }

    public void update(String title, String subTitle, String content, BoardType boardType) {    // 게시글 업데이트 메소드
        this.title = title;
        this.subTitle = subTitle;
        this.content = content;
        this.boardType = boardType;
    }

//    public Board toEntity(User user) {
//        return Board.builder()
//                .title(title)
//                .subTitle(subTitle)
//                .content(content)
//                .boardType(boardType)
//                .user(user)
//                .build();
//    }

}
