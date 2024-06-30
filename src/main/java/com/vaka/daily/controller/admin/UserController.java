package com.vaka.daily.controller.admin;

import com.vaka.daily.service.admin.UserService;
import com.vaka.daily.service.admin.UserTypeService;
import com.vaka.daily_client.client.blocked.UserTypeClient;
import com.vaka.daily_client.model.User;
import com.vaka.daily_client.model.UserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/user")
public class UserController {
    UserService userService;
    UserTypeService userTypeService;

    @Autowired
    public UserController(UserService userService, UserTypeService userTypeService) {
        this.userService = userService;
        this.userTypeService = userTypeService;
    }

    @GetMapping
    public String getHome(Model model) {
        List<User> users = userService.getAll();

        model.addAttribute("users", users);
        return "admin/user/index";
    }

    @GetMapping("/{id}")
    public String getUserById(@PathVariable("id") Integer id, Model model) {
        User user = userService.getById(id);

        model.addAttribute("user", user);
        return "admin/user/byId";
    }

    @GetMapping("/edit/{id}")
    public String editUserById(@PathVariable("id") Integer id, Model model) {
        User user = userService.getById(id);
        List<UserType> userTypes = userTypeService.getAll();

        List<String> userTypeNames = userTypes.stream().map(UserType::getName).map(StringUtils::capitalize).toList();

        model.addAttribute("user", user);
        model.addAttribute("userTypes", userTypeNames);
        return "admin/user/edit";
    }
}
