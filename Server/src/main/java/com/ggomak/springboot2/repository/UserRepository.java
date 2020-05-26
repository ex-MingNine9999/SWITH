package com.ggomak.springboot2.repository;

import com.ggomak.springboot2.domain.User;
import com.ggomak.springboot2.domain.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndSocialType(String email, SocialType socialType);

    Optional<User> findBySessionAddress(String sessionAddress);
}
