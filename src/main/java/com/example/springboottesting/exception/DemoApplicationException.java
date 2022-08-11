package com.example.springboottesting.exception;

public class DemoApplicationException extends RuntimeException {
    public DemoApplicationException(String msg) {
        super(msg);
    }
}
