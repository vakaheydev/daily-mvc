package com.vaka.daily_mvc.controller;

import com.vaka.daily_mvc.model.dto.UserDto;
import com.vaka.daily_mvc.service.authorization.AuthorizationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/authorization")
@Slf4j
public class AuthorizationController {
    AuthorizationService service;

    public AuthorizationController(AuthorizationService service) {
        this.service = service;
    }

    @GetMapping("/login")
    public String getLogin(
            @RequestParam(value = "from", defaultValue = "/") String fromURI,
            HttpServletRequest request,
            HttpServletResponse response) {
        addDeletableCookie("username", response);

        request.getSession().setAttribute("fromURI", fromURI);
        return "authorization/login";
    }

    @PostMapping("/login")
    public String postLogin(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @SessionAttribute("fromURI") String fromURI,
                            HttpServletResponse response) {
        UserDto userDto = UserDto.builder()
                .login(username)
                .password(password)
                .build();

        if (!service.authorize(userDto)) {
            return "authorization/login";
        }

        Cookie usernameCookie = createUsernameCookie(username);
        response.addCookie(usernameCookie);

        return "redirect:" + fromURI;
    }

    @PostMapping("/logout")
    public String postLogout(HttpServletRequest request, HttpServletResponse response) {
        addDeletableCookie("username", response);
        return "redirect:/";
    }

    private Cookie addDeletableCookie(String cookieName, HttpServletResponse response) {
        Cookie cookie = new Cookie(cookieName, null);
        cookie.setPath("/");
        cookie.setHttpOnly(true);
        cookie.setMaxAge(0);
        cookie.setAttribute("expires", "Thu, 01 Jan 1970 00:00:00 GMT");

        response.addCookie(cookie);
        return cookie;
    }

    private Cookie createUsernameCookie(String username) {
        Cookie usernameCookie = new Cookie("username", username);
        usernameCookie.setPath("/");
        usernameCookie.setHttpOnly(true);
        usernameCookie.setMaxAge(3000);
        return usernameCookie;
    }
}