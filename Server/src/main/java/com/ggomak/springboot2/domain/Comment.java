package com.ggomak.springboot2.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Primary;

import javax.persistence.*;

@Getter
@NoArgsConstructor
@Entity
@Table
public class Comment {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String title;
    private String comment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn      // 외래키 설정
    private Board board;
}
