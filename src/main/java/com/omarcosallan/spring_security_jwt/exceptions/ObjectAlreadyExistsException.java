package com.omarcosallan.spring_security_jwt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ObjectAlreadyExistsException extends StandardError {
    private String message;

    public ObjectAlreadyExistsException(String message) {
        this.message = message;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        problemDetail.setTitle("Object already exists.");
        problemDetail.setDetail(message);
        problemDetail.setProperty("timestamp", System.currentTimeMillis());

        return problemDetail;
    }
}
