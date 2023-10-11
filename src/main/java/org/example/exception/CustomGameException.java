package org.example.exception;

public class CustomGameException extends Exception {

    public CustomGameException(String message) {
        super(message);
    }

    public CustomGameException(String message, Throwable e) {
        super(message, e);
    }
}
