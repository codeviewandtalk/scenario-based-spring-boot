package com.codeviewandtalk.library.management.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {
    /**
     * Handles BookNotFoundException and returns a custom error response.
     *
     * @param ex the exception that was thrown
     * @return a ResponseEntity containing the error message and HTTP status
     */
    @ExceptionHandler(BookNotFoundException.class)
    public ResponseEntity<Map<String, String>> handleBookNotFound(BookNotFoundException ex) {
        Map<String, String> errorBody = new HashMap<>();
        errorBody.put("error", "Not Found");
        errorBody.put("message", ex.getMessage());
        errorBody.put("status", String.valueOf(HttpStatus.NOT_FOUND.value()));

        return new ResponseEntity<>(errorBody, HttpStatus.NOT_FOUND);
    }

}
