package com.vaka.daily_mvc.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/start")
public class StartController {
    @GetMapping
    public String getStart() {
        return "redirect:/user/start";
    }
}
