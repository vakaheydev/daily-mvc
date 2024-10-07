package com.vaka.daily.controller;

import com.vaka.daily.service.admin.UserService;
import com.vaka.dailyClient.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/start")
    public String getStart(@CookieValue(value = "username", defaultValue = "") String username, Model model) {
        if (username.equals(" ")) {
            return "redirect:authorization/login";
        }

        User user = userService.getByUniqueName(username);
        model.addAttribute("user", user);

        return "user/start";
    }
}
