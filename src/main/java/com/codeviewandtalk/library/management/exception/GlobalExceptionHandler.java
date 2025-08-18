package com.codeviewandtalk.library.management.exception;

import com.codeviewandtalk.library.management.dto.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

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

    /**
     * Exception when file size is more than 5MB
     * @param exc
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<String> handleMaxSizeException(MaxUploadSizeExceededException exc) {
        return ResponseEntity
                .badRequest()
                .body("File too large! Maximum allowed size is 5 MB.");
    }

    @ExceptionHandler(InvalidPublicationDateException.class)
    public ResponseEntity<ErrorResponse> handleInvalidPublicationDate(InvalidPublicationDateException ex) {
        ErrorResponse errorResponse = new ErrorResponse(ex.getMessage(), HttpStatus.BAD_REQUEST.value());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
