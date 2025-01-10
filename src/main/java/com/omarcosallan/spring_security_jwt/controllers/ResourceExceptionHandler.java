package com.omarcosallan.spring_security_jwt.controllers;

import com.omarcosallan.spring_security_jwt.exceptions.StandardError;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ResourceExceptionHandler {
    @ExceptionHandler(StandardError.class)
    public ResponseEntity<ProblemDetail> standardErrorException(StandardError e, HttpServletRequest request) {
        ProblemDetail pd = e.toProblemDetail(request);

        return ResponseEntity
                .status(pd.getStatus())
                .body(pd);
    }
}
