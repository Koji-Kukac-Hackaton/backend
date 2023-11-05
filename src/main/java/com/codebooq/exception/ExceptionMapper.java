package com.codebooq.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionMapper {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> toResponse(Exception e) {
        e.printStackTrace();
        ExceptionResponse response = new ExceptionResponse(e.getClass().getSimpleName(), e.getMessage());
        return ResponseEntity.status(400).body(response);
    }
}
