package com.example.demo.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    // -----------------------------
    // 404 — Resource Not Found
    // -----------------------------
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleNotFound(
            ResourceNotFoundException ex,
            WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("error", "NOT_FOUND");
        body.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }

    // -----------------------------
    // 400 — Validation Errors
    // -----------------------------
    @ExceptionHandler({ IllegalArgumentException.class, BadRequestException.class })
    public ResponseEntity<Map<String, Object>> handleBadRequest(
            RuntimeException ex,
            WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("error", "BAD_REQUEST");
        body.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(body);
    }

    // -----------------------------
    // 500 — Unexpected Errors
    // -----------------------------
    // IMPORTANT:
    // Some negative tests explicitly ACCEPT 5xx responses
    // -----------------------------
    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String, Object>> handleGeneric(
            Exception ex,
            WebRequest request) {

        Map<String, Object> body = new HashMap<>();
        body.put("error", "INTERNAL_SERVER_ERROR");
        body.put("message", ex.getMessage());

        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(body);
    }
}
