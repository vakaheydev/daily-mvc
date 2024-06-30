package com.vaka.daily.controller.advice;

import com.vaka.daily.exception.ServerIsNotAliveException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServerIsNotAliveException.class)
    public String handleServerIsNotAliveEx(ServerIsNotAliveException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/serverUnavailable";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex) {
        return "error/unexpectedError";
    }
}
