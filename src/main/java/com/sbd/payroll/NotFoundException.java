package com.sbd.payroll;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {

    String message;

    private static final long serialVersionUID = 1L;

    public NotFoundException() {
        super(" - ");
        this.message = "Error occured.";
    }

    public NotFoundException(String message) {
        super(message);
        this.message = message;
    }

    public NotFoundException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }
}


