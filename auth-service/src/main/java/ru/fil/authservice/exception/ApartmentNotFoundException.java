package ru.fil.authservice.exception;

public class ApartmentNotFoundException extends RuntimeException {
    public ApartmentNotFoundException() {
        super();
    }
    public ApartmentNotFoundException(String message) {
        super(message);
    }
}
