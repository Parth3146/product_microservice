package com.example.g3.exception;

public class DuplicateProductException extends RuntimeException {
    public DuplicateProductException(String s) {
        super(s);
    }
}
