package com.juliomesquita.cdc.shared.exceptions;

import com.juliomesquita.cdc.shared.utils.RestAPIErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Collections;
import java.util.Set;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<RestAPIErrorResponse> handleMethodArgumentNotValid(MethodArgumentNotValidException ex) {
        Set<String> errors = ex.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toSet());

        RestAPIErrorResponse errorResponse = new RestAPIErrorResponse(
                HttpStatus.BAD_REQUEST.value(),
                "Validation failed",
                errors
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<RestAPIErrorResponse> handleResourceNotFound(ResourceNotFoundException ex) {
        RestAPIErrorResponse errorResponse = new RestAPIErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                ex.getMessage(),
                Collections.emptySet()
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<RestAPIErrorResponse> handleGenericException(Exception ex) {
        // Em um ambiente de produção, é uma boa prática logar o erro.
        // ex.printStackTrace(); 

        RestAPIErrorResponse errorResponse = new RestAPIErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "An unexpected internal server error occurred.",
                Collections.singleton(ex.getMessage())
        );
        return new ResponseEntity<>(errorResponse, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
