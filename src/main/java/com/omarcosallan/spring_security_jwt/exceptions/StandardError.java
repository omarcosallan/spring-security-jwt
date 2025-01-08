package com.omarcosallan.spring_security_jwt.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

public class StandardError extends RuntimeException {

    public StandardError() {}

    public StandardError(String message) {
        super(message);
    }

    public ProblemDetail toProblemDetail() {
        ProblemDetail pb = ProblemDetail.forStatus(HttpStatus.INTERNAL_SERVER_ERROR);
        pb.setTitle("Internal server error");
        return pb;
    }
}