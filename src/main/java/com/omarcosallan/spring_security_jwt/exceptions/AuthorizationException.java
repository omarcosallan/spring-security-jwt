package com.omarcosallan.spring_security_jwt.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class AuthorizationException extends StandardError {
    private String message;

    public AuthorizationException(String message) {
        this.message = message;
    }

    @Override
    public ProblemDetail toProblemDetail(HttpServletRequest request) {
        return createProblemDetail(
                request,
                HttpStatus.UNAUTHORIZED,
                "Unauthorized access",
                message
        );
    }
}
