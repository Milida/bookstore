package com.sbd.payroll;

import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
@ResponseBody
public class BookNotFoundException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public BookNotFoundException(String message) {
        super(message);
    }

}


