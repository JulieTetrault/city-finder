package com.coveo.challenge.utils;

public class FileException extends RuntimeException {
    public FileException(String message, Exception exception) {
        super(message, exception);
    }
}
