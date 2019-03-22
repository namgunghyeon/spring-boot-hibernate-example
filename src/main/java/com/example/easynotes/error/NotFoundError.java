package com.example.easynotes.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class NotFoundError extends RuntimeException {

    public NotFoundError() {
        super();
    }
    public NotFoundError(String message, Throwable cause) {
        super(message, cause);
    }
    public NotFoundError(String message) {
        super(message);
    }
    public NotFoundError(Throwable cause) {
        super(cause);
    }
}
