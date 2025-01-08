package com.omarcosallan.spring_security_jwt.dto.request;

import java.util.Set;

public record SignupRequest(String name,
                            String email,
                            String password,
                            Set<String> roles) {
}
