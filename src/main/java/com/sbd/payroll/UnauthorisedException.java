package com.sbd.payroll;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class UnauthorisedException extends RuntimeException {

    String message;
    private static final long serialVersionUID = 1L;

    public UnauthorisedException() {
        super(" - ");
        this.message = "Error occured.";
    }

    public UnauthorisedException(String message) {
        super(message);
        this.message = message;
    }

    public UnauthorisedException(String message, Throwable throwable) {
        super(message, throwable);
        this.message = message;
    }
}
