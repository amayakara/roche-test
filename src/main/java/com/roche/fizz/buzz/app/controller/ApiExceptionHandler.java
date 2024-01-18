package com.roche.fizz.buzz.app.controller;

import com.roche.fizz.buzz.app.exception.BadRequestException;
import com.roche.fizz.buzz.app.exception.InvalidInputParamTypeException;
import com.roche.fizz.buzz.app.model.ErrorResponse;
import jakarta.validation.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {InvalidInputParamTypeException.class, ConstraintViolationException.class, IllegalArgumentException.class})
    protected ResponseEntity<ErrorResponse> handleBadRequestException(RuntimeException ex, WebRequest request) {
        String bodyOfResponse = new BadRequestException(ex.getLocalizedMessage()).toString();
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponse(bodyOfResponse));
    }


    @ExceptionHandler(value = {Exception.class})
    protected ResponseEntity<ErrorResponse> handleDefaultException(RuntimeException ex, WebRequest request) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(ex.getLocalizedMessage()));
    }
}
