package com.example.kanban.exceptions;

public class NoTaskFoundException extends RuntimeException {

    public NoTaskFoundException(String message) {
        super(message);
    }
}
