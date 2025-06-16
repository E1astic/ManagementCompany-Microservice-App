package ru.fil.addressservice.exception;

public class StreetNotFoundException extends RuntimeException {
    public StreetNotFoundException() {
    super();
  }
    public StreetNotFoundException(String message) {
        super(message);
    }
}
