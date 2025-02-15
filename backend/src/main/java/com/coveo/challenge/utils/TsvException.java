package com.coveo.challenge.utils;

public class TsvException extends RuntimeException {
    public TsvException(String message, Exception exception) {
        super(message, exception);
    }
}
