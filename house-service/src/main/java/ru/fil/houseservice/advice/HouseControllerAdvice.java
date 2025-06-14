package ru.fil.houseservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.fil.houseservice.exception.ApartmentNotFoundException;
import ru.fil.houseservice.model.dto.ExceptionResponse;

@RestControllerAdvice
public class HouseControllerAdvice {

    @ExceptionHandler(ApartmentNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(ApartmentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse("Данной собственности не существует"));
    }
}
