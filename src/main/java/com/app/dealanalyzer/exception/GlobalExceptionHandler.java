package com.app.dealanalyzer.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(DealAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponse> handleDealAlreadyExistException(DealAlreadyExistsException exception, WebRequest request){
        log.error("Exception occurred : {}, Request details : {}",
                exception.getMessage(),
                request.getDescription(false),exception);

        ExceptionResponse response = ExceptionResponse.builder()
                .message(exception.getMessage())
                .error("Already Exist")
                .status(406)
                .path(request.getDescription(false))
                .build();

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, WebRequest request){
        log.error("Invalid Arguments : {}, Request details : {}",
                exception.getMessage(),
                request.getDescription(false),exception);

        Map<String,String> errors = new HashMap<>();
        exception.getFieldErrors().forEach(
                e -> errors.put(e.getField(),e.getDefaultMessage())
        );

        return new ResponseEntity<>(errors,HttpStatus.BAD_REQUEST);
    }
}
