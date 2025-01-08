package com.omarcosallan.spring_security_jwt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class ObjectNotFoundException extends StandardError {
    private String message;

    public ObjectNotFoundException(String message) {
        this.message = message;
    }

    @Override
    public ProblemDetail toProblemDetail() {
        ProblemDetail problemDetail = ProblemDetail.forStatus(HttpStatus.NOT_FOUND);

        problemDetail.setTitle("Object not found.");
        problemDetail.setDetail(message);
        problemDetail.setProperty("timestamp", System.currentTimeMillis());

        return problemDetail;
    }
}
