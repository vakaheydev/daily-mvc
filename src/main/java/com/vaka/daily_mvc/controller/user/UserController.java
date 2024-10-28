package com.vaka.daily_mvc.controller.user;

import com.vaka.daily_client.exception.UserNotFoundException;
import com.vaka.daily_mvc.service.UserService;
import com.vaka.daily_client.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
@Slf4j
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

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException e) {
        log.error("User not found: {}", e.getMessage());

        return "redirect:/start";
    }
}
