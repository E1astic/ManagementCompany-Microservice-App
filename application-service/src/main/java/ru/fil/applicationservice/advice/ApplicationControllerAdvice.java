package ru.fil.applicationservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.fil.applicationservice.exception.ApplicationNotFoundException;
import ru.fil.applicationservice.exception.IncorrectStatusException;
import ru.fil.applicationservice.model.dto.SimpleMessageResponse;

@RestControllerAdvice
public class ApplicationControllerAdvice {

    @ExceptionHandler(IncorrectStatusException.class)
    public ResponseEntity<SimpleMessageResponse> handleException(IncorrectStatusException e) {
        return ResponseEntity.badRequest().body(new SimpleMessageResponse("Некорректный статус заявки"));
    }

    @ExceptionHandler(ApplicationNotFoundException.class)
    public ResponseEntity<SimpleMessageResponse> handleException(ApplicationNotFoundException e) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new SimpleMessageResponse("Заявки с указанным номером не существует"));
    }
}
