package com.app.dealanalyzer.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
@Builder
public class ExceptionResponse {
    private String error;
    private String message;
    private int status;
    private String path;
    private LocalDateTime timestamp = LocalDateTime.now();

}
