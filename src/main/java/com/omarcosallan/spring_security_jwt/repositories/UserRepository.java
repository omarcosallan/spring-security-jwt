package com.omarcosallan.spring_security_jwt.repositories;

import com.omarcosallan.spring_security_jwt.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UserRepository extends JpaRepository<User, UUID> {
    Optional<User> findByEmail(String email);
}
