package com.app.dealanalyzer;

public class DealAlreadyExistException extends RuntimeException {
    public DealAlreadyExistException(String message) {
        super(message);
    }
}
