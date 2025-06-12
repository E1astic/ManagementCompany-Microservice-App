package ru.fil.authservice.exception;

public class ExistingEmailException extends RuntimeException {
    public ExistingEmailException() {
        super();
    }
    public ExistingEmailException(String message) {
        super(message);
    }
}
