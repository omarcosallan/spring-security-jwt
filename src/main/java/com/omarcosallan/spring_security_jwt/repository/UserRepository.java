package com.omarcosallan.spring_security_jwt.repository;

import com.omarcosallan.spring_security_jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByEmail(String email);
    Optional<User> findByUsername(String username);
}
