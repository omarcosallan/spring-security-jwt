package com.omarcosallan.spring_security_jwt.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(AlreadyExistsException.class)
    public ResponseEntity<ProblemDetail> handleAlreadyExistsException(AlreadyExistsException e) {
        return createResponse(HttpStatus.CONFLICT,
                "Recurso já existe.",
                e.getMessage(),
                null
        );
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ProblemDetail> handleResourceNotFoundException(ResourceNotFoundException e) {
        return createResponse(HttpStatus.NOT_FOUND, "Recurso não encontrado.", e.getMessage(), null);
    }

    private ResponseEntity<ProblemDetail> createResponse(HttpStatus status, String title, String detail, Map<String, Object> properties) {
        ProblemDetail pd = ProblemDetail.forStatus(status);
        pd.setTitle(title);
        pd.setDetail(detail);
        pd.setProperty("timestamp", LocalDateTime.now());
        if (properties != null) {
            pd.setProperties(properties);
        }
        return ResponseEntity.status(pd.getStatus()).body(pd);
    }
}
