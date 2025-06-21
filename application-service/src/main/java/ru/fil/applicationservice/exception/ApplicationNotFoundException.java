package ru.fil.applicationservice.exception;

public class ApplicationNotFoundException extends RuntimeException {
    public ApplicationNotFoundException() {
        super();
    }
    public ApplicationNotFoundException(String message) {
        super(message);
    }
}
