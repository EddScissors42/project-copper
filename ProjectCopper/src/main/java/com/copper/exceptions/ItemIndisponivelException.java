package com.copper.exceptions;

public class ItemIndisponivelException extends RuntimeException {
    public ItemIndisponivelException(String message) {
        super(message);
    }
}