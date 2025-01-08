package com.omarcosallan.spring_security_jwt.dto.response;

import java.util.UUID;

public record JwtResponse(String token,
                          UUID id,
                          String name,
                          String email) {
}
