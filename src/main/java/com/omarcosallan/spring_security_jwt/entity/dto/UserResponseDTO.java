package com.omarcosallan.spring_security_jwt.entity.dto;

import com.omarcosallan.spring_security_jwt.entity.enumerated.Role;

public record UserResponseDTO(Long id,
                              String name,
                              String email,
                              String username,
                              Role role) {
}
