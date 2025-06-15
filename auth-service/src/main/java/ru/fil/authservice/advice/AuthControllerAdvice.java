package ru.fil.authservice.advice;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.fil.authservice.exception.ApartmentNotFoundException;
import ru.fil.authservice.exception.ExistingEmailException;
import ru.fil.authservice.model.dto.ExceptionResponse;


@RestControllerAdvice
public class AuthControllerAdvice {

    @ExceptionHandler({BadCredentialsException.class, UsernameNotFoundException.class})
    public ResponseEntity<ExceptionResponse> handleException(AuthenticationException ex) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(
                new ExceptionResponse("Неправильный логин или пароль"));
    }

    @ExceptionHandler(ExistingEmailException.class)
    public ResponseEntity<ExceptionResponse> handleException(ExistingEmailException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse("Пользователь с таким email уже существует"));
    }

    @ExceptionHandler(ApartmentNotFoundException.class)
    public ResponseEntity<ExceptionResponse> handleException(ApartmentNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                new ExceptionResponse("Указанной собственности не существует"));
    }
}
