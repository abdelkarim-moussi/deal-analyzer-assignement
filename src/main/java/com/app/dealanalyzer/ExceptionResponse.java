package com.app.dealanalyzer;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.List;

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
