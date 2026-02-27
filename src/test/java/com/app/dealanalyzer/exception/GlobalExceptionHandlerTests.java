package com.app.dealanalyzer.exception;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.core.MethodParameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.context.request.WebRequest;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class GlobalExceptionHandlerTests {

    private DealAlreadyExistsException dealAlreadyExistsException;
    private WebRequest request;

    private GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @BeforeEach
    void setUp(){

        dealAlreadyExistsException = new DealAlreadyExistsException("Deal Already Exists");

        request = Mockito.mock(WebRequest.class);

        ExceptionResponse response = ExceptionResponse.builder()
                .message(dealAlreadyExistsException.getMessage())
                .error("Already Exist")
                .status(400)
                .path(request.getDescription(false))
                .build();

    }

    @Test
    public void handleDealAlreadyExistsExceptionShouldReturnResponse(){

        ResponseEntity<ExceptionResponse> res = handler.handleDealAlreadyExistException(dealAlreadyExistsException,request);

        assertEquals(HttpStatus.BAD_REQUEST,res.getStatusCode());
        assertNotNull(res.getBody());
    }

    @Test
    public void handleMethodArgumentNotValidExceptionShouldReturnResponseErrors(){

        MethodArgumentNotValidException ex = Mockito.mock(MethodArgumentNotValidException.class);

        Map<String,String> errors = new HashMap<>();
        ex.getFieldErrors().forEach(e -> errors.put(e.getField(),e.getDefaultMessage()));

        ResponseEntity<Map<String,String>> res = handler.handleMethodArgumentNotValidException(ex,request);

        assertEquals(HttpStatus.BAD_REQUEST,res.getStatusCode());
        assertEquals(errors,res.getBody());
        assertNotNull(res.getBody());
    }
}
