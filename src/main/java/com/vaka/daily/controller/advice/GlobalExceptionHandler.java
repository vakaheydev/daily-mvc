package com.vaka.daily.controller.advice;

import com.vaka.daily.exception.ServerIsNotAliveException;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ServerIsNotAliveException.class)
    public String handleServerIsNotAliveEx(ServerIsNotAliveException ex, Model model) {
        model.addAttribute("errorMessage", ex.getMessage());
        return "error/serverUnavailable";
    }
}
