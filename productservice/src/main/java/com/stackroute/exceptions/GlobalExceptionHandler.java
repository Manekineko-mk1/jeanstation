package com.stackroute.exceptions;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {
    final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/uuuu - HH:mm:ss z");


    @ExceptionHandler(ProductAlreadyExistsException.class)
    public ResponseEntity<Object> handleProductAlreadyExistsException(
            ProductAlreadyExistsException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        body.put("timestamp", timeStamp);
        body.put("message", "Product already exists.");

        return new ResponseEntity<>(body, HttpStatus.CONFLICT);
    }

    @ExceptionHandler(ProductNotFoundException.class)
    public ResponseEntity<Object> handleNoProductFoundException(
            ProductNotFoundException ex, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        body.put("timestamp", timeStamp);
        body.put("message", "Product not found.");

        return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers,
            HttpStatus status, WebRequest request) {

        Map<String, Object> body = new LinkedHashMap<>();

        ZonedDateTime zonedDateTimeNow = ZonedDateTime.now(ZoneId.of("America/Montreal"));
        String timeStamp = zonedDateTimeNow.format(formatter);

        body.put("timestamp", timeStamp);
        body.put("status", status.value());

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());

        body.put("errors", errors);

        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }
}
