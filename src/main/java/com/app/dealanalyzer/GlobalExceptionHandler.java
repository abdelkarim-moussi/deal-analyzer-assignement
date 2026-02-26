package com.app.dealanalyzer;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DealAlreadyExistException.class)
    public ResponseEntity<ExceptionResponse> handleDealAlreadyExistException(DealAlreadyExistException exception, WebRequest request){

        Map<String,String> errors = new HashMap<>();
        ExceptionResponse response = ExceptionResponse.builder()
                .message(exception.getMessage())
                .error("Already Exist")
                .status(406)
                .path(request.getDescription(false))
                .build();

        return new ResponseEntity<>(response,HttpStatus.NOT_ACCEPTABLE);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request){

        Map<String,String> errors = new HashMap<>();
        exception.getFieldErrors().forEach(
                e -> errors.put(e.getField(),e.getDefaultMessage())
        );

        return new ResponseEntity<>(errors,HttpStatus.NOT_ACCEPTABLE);
    }
}
