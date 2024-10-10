package com.vaka.daily.controller;

import com.vaka.daily.service.admin.UserService;
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
        usernameCookie.setMaxAge(3000);

        response.addCookie(usernameCookie);

        return "redirect:/start";
    }

    @PostMapping("/logout")
    public String postLogout(HttpServletResponse response) {
        Cookie usernameCookie = new Cookie("username", null);
        usernameCookie.setPath("/");
        usernameCookie.setHttpOnly(true);
        usernameCookie.setMaxAge(0);
        usernameCookie.setAttribute("expires", "Thu, 01 Jan 1970 00:00:00 GMT");

        response.addCookie(usernameCookie);

        return "index";
    }
}