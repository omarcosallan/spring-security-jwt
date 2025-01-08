package com.omarcosallan.spring_security_jwt.controllers;

import com.omarcosallan.spring_security_jwt.exceptions.StandardError;
import org.springframework.http.ProblemDetail;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(StandardError.class)
    public ProblemDetail standardErrorException(StandardError e) {
        return e.toProblemDetail();
    }
}
