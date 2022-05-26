package com.odas.odas.exception;

import com.odas.odas.payload.ApiResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class CustomGlobalException {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiResponse> resourceNotFoundException(ResourceNotFoundException re) {
        String message = re.getMessage();
        ApiResponse apiRes = new ApiResponse(message, false);
        return new ResponseEntity<ApiResponse>(apiRes, HttpStatus.NOT_FOUND);
    }
}
