package com.vaka.daily_mvc.controller;

import com.vaka.daily_client.model.User;
import com.vaka.daily_mvc.model.form.UserRegistrationForm;
import com.vaka.daily_mvc.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextHolderStrategy;
import org.springframework.security.web.context.SecurityContextRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Slf4j
@RequestMapping("/registration")
@Controller
public class RegistrationController {
    AuthenticationManager authenticationManager;
    UserService userService;
    SecurityContextRepository securityContextRepository;

    @Autowired
    public RegistrationController(AuthenticationManager authenticationManager, UserService userService,
                                  SecurityContextRepository securityContextRepository) {
        this.authenticationManager = authenticationManager;
        this.userService = userService;
        this.securityContextRepository = securityContextRepository;
    }

    @GetMapping
    public String showForm(Model model) {
        model.addAttribute("userForm", new UserRegistrationForm());

        return "registration";
    }

    @PostMapping
    public String postForm(@Valid UserRegistrationForm userForm, BindingResult bindingResult, HttpServletRequest request, HttpServletResponse response) {
        if (bindingResult.hasErrors()) {
            return "registration";
        }

        User user = User.builder()
                .login(userForm.getLogin())
                .password(userForm.getPassword())
                .firstName(" ")
                .secondName(" ")
                .patronymic(" ")
                .telegramId(userForm.getTelegramId())
                .build();

        userService.create(user);

        Authentication authenticationRequest = UsernamePasswordAuthenticationToken.unauthenticated(userForm.getLogin(), userForm.getPassword());
        Authentication authenticationResponse = authenticationManager.authenticate(authenticationRequest);

        SecurityContextHolderStrategy securityContextHolderStrategy = SecurityContextHolder.getContextHolderStrategy();
        SecurityContext ctx = securityContextHolderStrategy.createEmptyContext();
        ctx.setAuthentication(authenticationResponse);

        securityContextRepository.saveContext(ctx, request, response);

        return "redirect:/start";
    }
}
