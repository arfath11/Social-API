package com.example.with_2.exception;

public class PageNotFoundException extends  RuntimeException {
    public PageNotFoundException(String message) {
        super(message);
    }
}
