package com.ggomak.springboot2.repository;

import com.ggomak.springboot2.domain.Content;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContentRepository extends JpaRepository<Content, Long> {

    Content findByContentNumber(Long contentNumber);
}
