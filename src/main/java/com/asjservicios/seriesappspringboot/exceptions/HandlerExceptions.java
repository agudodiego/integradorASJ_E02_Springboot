package com.asjservicios.seriesappspringboot.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(PlataformaException.class)
    public ResponseEntity<?> handlerNotFoundException(PlataformaException e) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", Boolean.FALSE);
        response.put("message", e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
