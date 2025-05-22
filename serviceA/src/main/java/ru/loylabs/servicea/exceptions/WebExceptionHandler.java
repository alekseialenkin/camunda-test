package ru.loylabs.servicea.exceptions;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@RequiredArgsConstructor
public class WebExceptionHandler {
    @ExceptionHandler(RequestNotFoundException.class)
    public ResponseEntity<String> handleRequestNotFoundException(RequestNotFoundException ex) {
        return ResponseEntity.badRequest().body(ex.getMessage());
    }
}
