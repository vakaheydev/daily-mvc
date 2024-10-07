package com.vaka.daily.controller.admin;

import com.vaka.daily.service.admin.UserService;
import com.vaka.daily.service.admin.UserTypeService;
import com.vaka.dailyClient.model.Schedule;
import com.vaka.dailyClient.model.User;
import com.vaka.dailyClient.model.UserType;
import com.vaka.dailyClient.model.UserTypes;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin/user")
public class AdminUserController {
    UserService userService;
    UserTypeService userTypeService;

    @Autowired
    public AdminUserController(UserService userService, UserTypeService userTypeService) {
        this.userService = userService;
        this.userTypeService = userTypeService;
    }

    @GetMapping
    public String get(Model model) {
        List<User> users = userService.getAll();
        users.sort(Comparator.comparingInt(User::getId));

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
    public String getEditById(@PathVariable("id") Integer id, Model model) {
        User user = userService.getById(id);

        List<String> userTypeNames = getUserTypeNames();

        model.addAttribute("user", user);
        model.addAttribute("userTypes", userTypeNames);
        return "admin/user/edit";
    }

    @PutMapping("/edit/{id}")
    public String putEditById(@ModelAttribute User user, Model model) {
        List<String> userTypeNames = getUserTypeNames();

        userService.updateById(user.getId(), user);

        model.addAttribute("user", user);
        model.addAttribute("userTypes", userTypeNames);

        return "admin/user/edit";
    }

    private List<String> getUserTypeNames() {
        List<UserType> userTypes = userTypeService.getAll();
        return userTypes.stream().map(UserType::getName).map(StringUtils::capitalize).toList();
    }
}
