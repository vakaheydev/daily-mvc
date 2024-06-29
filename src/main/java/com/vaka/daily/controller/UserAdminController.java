package com.vaka.daily.controller;

import com.vaka.daily.service.UserService;
import com.vaka.daily_client.client.blocked.UserClient;
import com.vaka.daily_client.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
public class UserAdminController {
    UserService userService;

    @Autowired
    public UserAdminController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/user")
    public String getHome(Model model) {
        List<User> users = userService.getAll();

        model.addAttribute("users", users);
        return "index";
    }

    @GetMapping("/user/{id}")
    public String getUserById(@PathVariable("id") Integer id, Model model) {
        User user = userService.getById(id);

        model.addAttribute("user", user);
        return "user/byId";
    }
}
