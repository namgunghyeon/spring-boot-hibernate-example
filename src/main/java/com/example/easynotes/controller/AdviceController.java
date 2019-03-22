package com.example.easynotes.controller;

import com.example.easynotes.error.ErrorMessage;
import com.example.easynotes.error.NotFoundError;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

@ControllerAdvice
public class AdviceController {
    @ExceptionHandler(value = { NotFoundError.class })
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    protected ErrorMessage handleConflict(RuntimeException ex, WebRequest request) {
        ErrorMessage em = new ErrorMessage();
        em.setStatus(HttpStatus.NOT_FOUND.toString());
        em.setMessage(ex.getMessage());
        return em;
    }

    @ExceptionHandler(value = { IllegalArgumentException.class })
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    protected ErrorMessage handleInternalError(RuntimeException ex, WebRequest request) {
        ErrorMessage em = new ErrorMessage();
        em.setStatus(HttpStatus.INTERNAL_SERVER_ERROR.toString());
        em.setMessage(ex.getMessage());
        return em;
    }
}
