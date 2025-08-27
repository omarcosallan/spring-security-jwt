package com.omarcosallan.spring_security_jwt.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public record LoginDTO(@NotNull(message = "E-mail é obrigatório")
                       @Size(min = 1, message = "E-mail é obrigatório")
                       @Email(message = "E-mail deve ter um formato válido")
                       String email,
                       @NotNull(message = "Senha é obrigatória")
                       @Size(min = 6, message = "A senha deve ter pelo menos 6 caracteres")
                       String password) {
}
