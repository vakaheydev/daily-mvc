package com.vaka.daily_mvc.controller;

import com.vaka.daily_client.model.UserNotFoundException;
import com.vaka.daily_client.model.dto.UserDTO;
import com.vaka.daily_mvc.service.authorization.AuthorizationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@Controller
@RequestMapping("/start")
public class StartController {
    AuthorizationService authorizationService;

    @Autowired
    public StartController(AuthorizationService authorizationService) {
        this.authorizationService = authorizationService;
    }

    @GetMapping
    public String getStart(@CookieValue(value = "username", defaultValue = "") String username, Model model) {
        UserDTO userDTO = new UserDTO();
        userDTO.setLogin(username);

        if (!authorizationService.authorize(userDTO)) {
            return "redirect:/authorization/login";
        }

        return "redirect:/user/start";
    }
}
