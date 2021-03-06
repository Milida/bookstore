package com.sbd.payroll;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.CONFLICT)
public class ConflictException extends RuntimeException {

    String message;
    private static final long serialVersionUID = 1L;

    public ConflictException() {
        super(" - ");
        this.message = "Error occured.";
    }

    public ConflictException(String message) {
        super(message);
        this.message = message;
    }

    public ConflictException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }
}