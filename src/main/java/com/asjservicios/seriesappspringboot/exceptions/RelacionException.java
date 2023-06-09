package com.asjservicios.seriesappspringboot.exceptions;

import org.springframework.http.HttpStatus;

public class RelacionException extends Exception{

    HttpStatus status;

    public RelacionException() {
    }

    public RelacionException(String message) {
        super(message);
    }

    public RelacionException(String message, HttpStatus status) {
        super(message);
        this.status = status;
    }
}
