package com.vaka.daily_mvc.exception.handler;

import com.vaka.daily_client.exception.ServerNotRespondingException;
import com.vaka.daily_mvc.exception.ServerIsNotAliveException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.util.Arrays;

@ControllerAdvice
@Slf4j
@Order(value = 1000000)
public class GlobalExceptionHandler {
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler(NoResourceFoundException.class)
    public String handle(NoResourceFoundException ex, Model model) {
        log.error("No resource: {}", ex.getResourcePath());
        model.addAttribute("errorMsg", "No specified resource: " + ex.getResourcePath());
        model.addAttribute("errorName", "No such resource");

        return "error/defaultError";
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public String handle(MethodArgumentNotValidException ex, Model model) {
        log.error("Argument mismatch", ex);
        model.addAttribute("errorMsg", "Argument mismatch: " + ex.getFieldError());
        model.addAttribute("errorName", "MethodArgumentNotValidException");

        return "error/defaultError";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler({ServerIsNotAliveException.class, ServerNotRespondingException.class})
    public String handle(Exception ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/serverUnavailable";
    }

    @ResponseStatus(HttpStatus.METHOD_NOT_ALLOWED)
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public String handle(HttpRequestMethodNotSupportedException ex, Model model) {
        log.error("Incorrect HTTP method", ex);
        model.addAttribute("errorMsg", "Method not allowed: " + ex.getMethod());
        model.addAttribute("errorName", "HttpRequestMethodNotSupportedException");

        return "error/defaultError";
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public String handle(RuntimeException ex, Model model) {
        log.error("Runtime exception", ex);

        String msg = "Something went wrong. Please return back and try again";

//        if (ex.getMessage().startsWith("Data integrity error")) {
//            msg = "Already exists";
//        }

        model.addAttribute("errorMsg", msg);
        model.addAttribute("errorName", "Error");

        return "error/defaultError";
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public String handle(Exception ex) {
        log.error("Unexpected Error", ex);
        return "error/unexpectedError";
    }
}
