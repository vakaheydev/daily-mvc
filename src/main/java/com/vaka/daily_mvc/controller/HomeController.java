package com.vaka.daily_mvc.controller;

import com.vaka.daily_mvc.service.authorization.AuthorizationService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {
    AuthorizationService service;

    public HomeController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping
    public String getHome(@CookieValue(value = "username", required = false) String username, Model model) {
        if (service.existsUser(username)) {
            model.addAttribute("username", username);
        }

        return "index";
    }
}
