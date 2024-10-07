package com.vaka.daily.controller;

import com.vaka.daily.service.admin.UserService;
import com.vaka.dailyClient.model.User;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/authorization")
@Slf4j
public class AuthorizationController {
    UserService userService;

    @Autowired
    public AuthorizationController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLogin() {
//        model.addAttribute("userName", "");

        return "authorization/login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam("username") String username, HttpServletResponse response) {
        Cookie usernameCookie = new Cookie("username", username);
        usernameCookie.setPath("/");
        usernameCookie.setHttpOnly(true);
        usernameCookie.setMaxAge(5);

        response.addCookie(usernameCookie);

        User user = userService.getByUniqueName(username);
        log.info(user.toString());

        return "redirect:/start";
    }
}