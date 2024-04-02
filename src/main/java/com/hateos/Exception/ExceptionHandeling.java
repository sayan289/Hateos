package com.hateos.Exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionHandeling extends RuntimeException{

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<String> exceptionHandling(CustomException ex){
        String msg = ex.getMessage();
        return new ResponseEntity<>(msg, HttpStatus.NOT_FOUND);
    }
}
