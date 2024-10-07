package com.vaka.daily.exception.handler;

import com.vaka.daily.exception.ServerIsNotAliveException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

@ControllerAdvice
@Slf4j
@Order(value = 1000000)
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResourceFoundException.class)
    public String handle(NoResourceFoundException ex, Model model) {
        log.error("No resource: {}", ex.getResourcePath());
        model.addAttribute("errorMsg", "No specified resource: " + ex.getResourcePath());
        model.addAttribute("errorName", "NoResourceFoundException");

        return "error/defaultError";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handle(MethodArgumentNotValidException ex, Model model) {
        log.error("Argument mismatch: {}", ex.getFieldError());
        model.addAttribute("errorMsg", "Argument mismatch: " + ex.getFieldError());
        model.addAttribute("errorName", "MethodArgumentNotValidException");

        return "error/defaultError";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(ServerIsNotAliveException.class)
    public String handle(ServerIsNotAliveException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/serverUnavailable";
    }
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handle(Exception ex) {
        log.error("Unexpected Error: {}", ex.toString());
        return "error/unexpectedError";
    }
}