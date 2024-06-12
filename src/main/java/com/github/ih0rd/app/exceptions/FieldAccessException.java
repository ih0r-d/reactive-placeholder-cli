package com.github.ih0rd.app.exceptions;

public class FieldAccessException extends Exception {
    public FieldAccessException(String message) {
        super(message);
    }

    public FieldAccessException(String message, Throwable cause) {
        super(message, cause);
    }
}
