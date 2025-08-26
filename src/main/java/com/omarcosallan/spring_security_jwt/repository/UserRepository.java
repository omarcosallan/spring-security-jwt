package com.omarcosallan.spring_security_jwt.repository;

import com.omarcosallan.spring_security_jwt.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
