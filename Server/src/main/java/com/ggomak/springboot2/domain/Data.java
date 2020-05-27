package com.ggomak.springboot2.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;

@NoArgsConstructor  // 생성자 생성
@Entity // 테이블과 1대1 매칭
@Table  // 테이블 설정
@Getter // get 메소드 생성
public class Data {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne(fetch = FetchType.LAZY)
    private User user;

    @OneToOne(fetch = FetchType.LAZY)
    private Content content;

    @Column
    @Length(max = 5000)
    private String concentrateData;

    @Builder
    public Data(User user, Content content, String concentrateData) {
        this.user = user;
        this.content = content;
        this.concentrateData = concentrateData;
    }

    public void setData(String concentrateData){
        this.concentrateData = concentrateData;
    }
}
