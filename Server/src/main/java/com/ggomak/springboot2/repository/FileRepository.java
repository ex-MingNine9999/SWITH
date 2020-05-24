package com.ggomak.springboot2.repository;

import com.ggomak.springboot2.domain.Files;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FileRepository extends JpaRepository<Files, Long> {
    Files findByStoredFileName(String fileName);
}
