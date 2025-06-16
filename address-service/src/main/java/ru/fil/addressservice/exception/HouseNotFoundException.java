package ru.fil.addressservice.exception;

public class HouseNotFoundException extends RuntimeException {
  public HouseNotFoundException() {
    super();
  }
  public HouseNotFoundException(String message) {
    super(message);
  }
}
