package com.omarcosallan.spring_security_jwt.dto.request;

import com.omarcosallan.spring_security_jwt.domain.ERole;

import java.util.Set;

public record SignupRequest(String name,
                            String email,
                            String password,
                            Set<ERole> roles) {
}
