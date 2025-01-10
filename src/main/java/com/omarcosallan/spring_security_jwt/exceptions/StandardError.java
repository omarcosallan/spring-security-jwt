package com.omarcosallan.spring_security_jwt.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.net.URI;

public abstract class StandardError extends RuntimeException {

    public StandardError() {}

    public StandardError(String message) {
        super(message);
    }

    protected ProblemDetail createProblemDetail(HttpServletRequest request, HttpStatus status, String title, String detail) {
        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle(title);
        problemDetail.setDetail(detail);
        problemDetail.setInstance(URI.create(request.getRequestURI()));
        return problemDetail;
    }

    public abstract ProblemDetail toProblemDetail(HttpServletRequest request);
}