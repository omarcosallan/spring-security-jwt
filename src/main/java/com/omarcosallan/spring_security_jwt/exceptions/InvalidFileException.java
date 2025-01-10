package com.omarcosallan.spring_security_jwt.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class InvalidFileException extends StandardError {
    private final String message;

    public InvalidFileException(String message) {
        this.message = message;
    }

    @Override
    public ProblemDetail toProblemDetail(HttpServletRequest request) {
        return createProblemDetail(
                request,
                HttpStatus.BAD_REQUEST,
                "Invalid File",
                message
        );
    }
}
