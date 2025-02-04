package com.vaka.daily_mvc.controller;

import com.vaka.daily_client.exception.UserNotFoundException;
import com.vaka.daily_client.model.User;
import com.vaka.daily_mvc.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class LoginController {
    UserService userService;
    AuthenticationManager authenticationManager;
    SecurityContextRepository securityContextRepository;

    @Autowired
    public LoginController(UserService userService, AuthenticationManager authenticationManager) {
        this.userService = userService;
        this.authenticationManager = authenticationManager;
    }

    @GetMapping("/login")
    public String showLogin() {
        return "login";
    }

    @GetMapping("/login/{tgId}")
    public String showLoginTgId(@PathVariable("tgId") Long telegramId,
                                Model model) {

        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        userService.getByUniqueName(username); // if no such user throws an exception

        model.addAttribute("username", username);
        model.addAttribute("tgId", telegramId);

        return "loginTgId";
    }

    @PostMapping("/login/{tgId}")
    public String loginTgId(@RequestParam("username") String username,
                            @RequestParam("password") String password,
                            @PathVariable("tgId") Long telegramId,
                            Model model,
                            HttpServletRequest request,
                            HttpServletResponse response) {

        Authentication authReq = UsernamePasswordAuthenticationToken.unauthenticated(
                username, password);

        Authentication authResp;

        try {
            authResp = authenticationManager.authenticate(authReq);
        } catch (BadCredentialsException ex) {
            model.addAttribute("incorrectPassword", true);
            model.addAttribute("username", username);
            model.addAttribute("tgId", telegramId);

            return "loginTgId";
        }

        User user = userService.getByUniqueName(username);
        user.setTelegramId(telegramId);
        userService.updateById(user.getId(), user);

        return "loginTgIdSuccess";
    }

    @DeleteMapping("/login/{tgId}")
    public String unbindTgId() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        User user = userService.getByUniqueName(username);
        user.setTelegramId(null);

        userService.updateById(user.getId(), user);


        return "redirect:/start";
    }
}
