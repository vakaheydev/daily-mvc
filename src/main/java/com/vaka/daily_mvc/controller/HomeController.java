package com.vaka.daily_mvc.controller;

import com.vaka.daily_mvc.service.authorization.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/home")
public class HomeController {
    AuthorizationService service;

    public HomeController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping
    public String getHome() {
        return "index";
    }
}
