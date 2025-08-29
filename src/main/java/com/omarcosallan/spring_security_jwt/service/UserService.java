package com.omarcosallan.spring_security_jwt.service;

import com.omarcosallan.spring_security_jwt.entity.User;
import com.omarcosallan.spring_security_jwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }
}
