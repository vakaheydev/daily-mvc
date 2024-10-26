package com.vaka.daily_mvc.controller;

import com.vaka.daily_mvc.model.dto.UserDto;
import com.vaka.daily_mvc.service.authorization.AuthorizationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/authorization")
@Slf4j
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String getLogin() {
        return "authorization/login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            HttpServletResponse response) {
        UserDto userDto = UserDto.builder()
                .login(username)
                .password(password)
                .build();

        if (!service.authorize(userDto)) {
            return "authorization/login";
        }

        Cookie usernameCookie = new Cookie("username", username);
        usernameCookie.setPath("/");
        usernameCookie.setHttpOnly(true);
        usernameCookie.setMaxAge(3000);

        response.addCookie(usernameCookie);

        return "redirect:/";
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