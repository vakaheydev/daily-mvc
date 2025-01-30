package com.vaka.daily_mvc.controller;

import com.vaka.daily_client.model.UserTypes;
import com.vaka.daily_mvc.service.authorization.AuthorizationService;
import org.springframework.security.core.context.SecurityContextHolder;
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
    public String getHome(Model model) {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        if (service.existsUser(username)) {
            model.addAttribute("username", username);
        }

        boolean isAdmin = false;
        if (service.hasRole(username, UserTypes.ADMIN)) {
            isAdmin = true;
        }

        model.addAttribute("isAdmin", isAdmin);

        return "index";
    }
}
