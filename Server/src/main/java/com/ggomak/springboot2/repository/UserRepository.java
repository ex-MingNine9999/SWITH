package com.ggomak.springboot2.repository;

import com.ggomak.springboot2.domain.User;
import com.ggomak.springboot2.domain.enums.SocialType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email);

    Optional<User> findByEmailAndSocialType(String email, SocialType socialType);
}
