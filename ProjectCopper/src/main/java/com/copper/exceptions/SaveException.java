package com.copper.exceptions;

public class SaveException extends RuntimeException {
    public SaveException(String message) {
        super(message);
    }
}