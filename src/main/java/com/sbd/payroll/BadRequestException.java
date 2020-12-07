package com.sbd.payroll;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class BadRequestException extends RuntimeException {

    String message;
    private static final long serialVersionUID = 1L;

    public BadRequestException() {
        super(" - ");
        this.message = "Error occured.";
    }

    public BadRequestException(String message) {
        super(message);
        this.message = message;
    }

    public BadRequestException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }
}