package com.asjservicios.seriesappspringboot.exceptions;

import org.springframework.http.HttpStatus;

public class UsuarioException extends Exception{

    HttpStatus status;

    public UsuarioException() {
    }

    public UsuarioException(String message) {
        super(message);
    }

    public UsuarioException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
