package ru.fil.notificationservice.exception;

public class EmailSendingException extends RuntimeException {
  public EmailSendingException() {
    super();
  }
  public EmailSendingException(String message) {
    super(message);
  }
}
