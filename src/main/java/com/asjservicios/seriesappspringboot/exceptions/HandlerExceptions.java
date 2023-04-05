package com.asjservicios.seriesappspringboot.exceptions;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

@ControllerAdvice
public class HandlerExceptions {

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<?> handlerNoSuchElementException(NoSuchElementException e) {
        String msg = "El elemento no se encuentra en la BD";
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(responseMaker(Boolean.FALSE, msg));
    }

    @ExceptionHandler(SerieException.class)
    public ResponseEntity<?> handlerSerieException(SerieException e) {
        return ResponseEntity.status(HttpStatus.CONFLICT).body(responseMaker(Boolean.FALSE, e.getMessage()));
    }

    @ExceptionHandler(UsuarioException.class)
    public ResponseEntity<?> handlerUsuarioException(UsuarioException e) {
        return ResponseEntity.status(e.status).body(responseMaker(Boolean.FALSE, e.getMessage()));
    }

    @ExceptionHandler(RelacionException.class)
    public ResponseEntity<?> handlerRelacionException(RelacionException e) {
        return ResponseEntity.status(e.status).body(responseMaker(Boolean.FALSE, e.getMessage()));
    }

    // Metodo interno
    private Map<String, Object> responseMaker(Boolean bol, String message) {
        Map<String, Object> response = new HashMap<>();
        response.put("success", bol);
        response.put("message", message);
        return response;
    }
}
