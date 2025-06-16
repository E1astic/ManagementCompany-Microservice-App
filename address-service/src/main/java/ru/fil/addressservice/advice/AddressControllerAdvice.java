package ru.fil.addressservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.fil.addressservice.exception.ApartmentNotFoundException;
import ru.fil.addressservice.exception.HouseNotFoundException;
import ru.fil.addressservice.exception.StreetNotFoundException;
import ru.fil.addressservice.model.dto.ExceptionResponse;

@RestControllerAdvice
public class AddressControllerAdvice {

    @ExceptionHandler(ApartmentNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(ApartmentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
                new ExceptionResponse("Указанной собственности не существует"));
    }

    @ExceptionHandler(StreetNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(StreetNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse("Указанной улицы не существует"));
    }

    @ExceptionHandler(HouseNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(HouseNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse("Указанного дома не существует"));
    }
}
