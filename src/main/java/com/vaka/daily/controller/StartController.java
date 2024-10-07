package com.vaka.daily.controller;

import com.vaka.daily.service.authorization.AuthorizationService;
import com.vaka.dailyClient.model.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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

//    @PostMapping
//    public String postStart(@ModelAttribute("userDTO") UserDTO userDTO) {
//        return "";
//    }
}
