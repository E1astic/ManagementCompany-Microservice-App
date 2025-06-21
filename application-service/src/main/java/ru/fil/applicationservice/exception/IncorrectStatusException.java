package ru.fil.applicationservice.exception;

public class IncorrectStatusException extends RuntimeException {
    public IncorrectStatusException() {
        super();
    }
    public IncorrectStatusException(String message) {
        super(message);
    }
}
