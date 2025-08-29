package com.omarcosallan.spring_security_jwt.service;

import com.omarcosallan.spring_security_jwt.entity.dto.UserResponseDTO;
import com.omarcosallan.spring_security_jwt.mapper.UserMapper;
import com.omarcosallan.spring_security_jwt.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final UserMapper userMapper;

    public List<UserResponseDTO> findAll() {
        return userRepository.findAll().stream().map(userMapper::toDTO).toList();
    }
}
