package com.example.easynotes.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class InvalidParamter extends RuntimeException {
    public InvalidParamter() {
        super();
    }
    public InvalidParamter(String message, Throwable cause) {
        super(message, cause);
    }
    public InvalidParamter(String message) {
        super(message);
    }
    public InvalidParamter(Throwable cause) {
        super(cause);
    }
}
