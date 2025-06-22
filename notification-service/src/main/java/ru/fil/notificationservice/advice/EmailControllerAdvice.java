package ru.fil.notificationservice.advice;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.fil.notificationservice.exception.EmailSendingException;
import ru.fil.notificationservice.model.dto.SimpleMessageResponse;

@RestControllerAdvice
public class EmailControllerAdvice {

    @ExceptionHandler(EmailSendingException.class)
    public ResponseEntity<SimpleMessageResponse> handleException(EmailSendingException ex) {
        return ResponseEntity.internalServerError().body(
                new SimpleMessageResponse("Ошибка отправки email-сообщения"));
    }
}
