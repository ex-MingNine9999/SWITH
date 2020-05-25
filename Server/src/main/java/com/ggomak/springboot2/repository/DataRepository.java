package com.ggomak.springboot2.repository;

import com.ggomak.springboot2.domain.Content;
import com.ggomak.springboot2.domain.Data;
import com.ggomak.springboot2.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DataRepository extends JpaRepository<Data, Long> {

    Data findByUserAndContent(User user, Content content);
}
