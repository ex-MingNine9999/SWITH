package com.ggomak.springboot2.domain;

import com.ggomak.springboot2.domain.enums.BoardType;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter // get 메소드 생성
@NoArgsConstructor  // 생성자 생성
@Entity // 테이블과 1대1 매칭
@Table  // 테이블 설정
public class Files {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String originFileName;

    @Column
    private String storedFileName;

    @Column
    private String filePath;

    @Column
    private long fileSize;

    @Builder
    public Files(String originFileName, String storedFileName, String filePath, long fileSize) {
        this.originFileName = originFileName;
        this.storedFileName = storedFileName;
        this.filePath = filePath;
        this.fileSize = fileSize;
    }
}
