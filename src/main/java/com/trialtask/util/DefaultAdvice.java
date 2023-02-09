package com.trialtask.util;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DefaultAdvice {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Response> handleException(Exception e) {
        String message = String.format("%s %s", LocalDateTime.now(), e.getMessage());
        Response response = new Response(message);
        return ResponseEntity
                .badRequest()
                .body(response);
    }
}
