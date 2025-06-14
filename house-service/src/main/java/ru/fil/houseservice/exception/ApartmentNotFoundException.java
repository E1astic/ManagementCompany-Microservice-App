package ru.fil.houseservice.exception;

public class ApartmentNotFoundException extends RuntimeException {
    public ApartmentNotFoundException() {
        super();
    }
    public ApartmentNotFoundException(String message) {
        super(message);
    }
}
