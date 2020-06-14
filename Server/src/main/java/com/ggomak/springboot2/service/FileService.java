package com.ggomak.springboot2.service;

import com.ggomak.springboot2.domain.Files;
import com.ggomak.springboot2.repository.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RequiredArgsConstructor
@Service
public class FileService {

    private final FileRepository fileRepository;
    private String filePath = "/Users/ggomak/Desktop/SpringBoot2File/";

    @Transactional
    public String upload(MultipartFile multipartFile) {
        String dirTimeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyMMdd"));
        String fileTimeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HHmmss"));

        File dirCheck = new File(filePath + dirTimeStamp);

        if(!dirCheck.isDirectory()){    // 디렉토리 유무를 체크하고 없을경우 생성
            dirCheck.mkdir();
        }

        File file = new File(filePath + dirTimeStamp + "/" + fileTimeStamp + "_" + multipartFile.getOriginalFilename());

        try {
            multipartFile.transferTo(file); // 파일 저장
        } catch (Exception e) {
            e.printStackTrace();
        }

        Files files = new Files(multipartFile.getOriginalFilename(), fileTimeStamp + "_" + multipartFile.getOriginalFilename(), filePath + dirTimeStamp + "/", multipartFile.getSize());    // Files 객체 생

        fileRepository.save(files);

        return fileTimeStamp;
    }
}
