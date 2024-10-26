package com.vaka.daily_mvc.controller;

import com.vaka.daily_client.model.UserTypes;
import com.vaka.daily_mvc.service.authorization.AuthorizationService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class AdminController {
    AuthorizationService authorizationService;

    public AdminController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping
    public String get(@CookieValue(value = "username", required = false) String username) {
        if (!authorizationService.hasRole(username, UserTypes.ADMIN)) {
            return "redirect:/authorization/login";
        }

        return "/admin/index";
    }
}
