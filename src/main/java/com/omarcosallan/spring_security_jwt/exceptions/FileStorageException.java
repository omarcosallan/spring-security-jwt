package com.omarcosallan.spring_security_jwt.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class FileStorageException extends StandardError {
    private String message;

    public FileStorageException(String message) {
        this.message = message;
    }

    @Override
    public ProblemDetail toProblemDetail(HttpServletRequest request) {
        return createProblemDetail(
                request,
                HttpStatus.UNAUTHORIZED,
                "File storage error",
                message
        );
    }
}
