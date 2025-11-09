package com.example.spacecatsmarket.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> handleValidationError(
            MethodArgumentNotValidException ex) {

        String detail = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Validation error");

        Map<String, Object> body = new HashMap<>();
        body.put("type", "https://spacecatsmarket.com/problems/validation-error");
        body.put("title", "Bad Request");
        body.put("status", HttpStatus.BAD_REQUEST.value());
        body.put("detail", detail);
        body.put("instance", "/api/v1/products");

        return ResponseEntity.badRequest().body(body);
    }
    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Map<String, Object>> handleProductNotFound(ProductNotFoundException ex) {
        Map<String, Object> body = new HashMap<>();
        body.put("type", "https://spacecatsmarket.com/problems/product-not-found");
        body.put("title", "Not Found");
        body.put("status", HttpStatus.NOT_FOUND.value());
        body.put("detail", ex.getMessage());
        body.put("instance", "/api/v1/products");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(body);
    }
}
