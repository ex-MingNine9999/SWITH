package com.ggomak.springboot2.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.io.Serializable;

@Getter // get 메소드 생성
@NoArgsConstructor  // 생성자 생성
@Entity // 테이블과 1대1 매칭
@Table  // 테이블 설정
public class Content extends BaseEntity implements Serializable {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long contentNumber;

    @Column
    private String contentName;

    @Column
    private Long contentSize;

    @Column
    private Long contentTime;

    @Builder
    public Content(String contentName, Long contentNumber ,Long contentSize, Long contentTime) {
        this.contentName = contentName;
        this.contentNumber = contentNumber;
        this.contentSize = contentSize;
        this.contentTime = contentTime;
    }
}
