package com.omarcosallan.spring_security_jwt.dto.response;

import java.time.Instant;
import java.util.UUID;

public record JwtResponse(String token,
                          Instant expiresAt,
                          UUID id,
                          String name,
                          String email) {
}
