package com.vaka.daily_mvc.controller.admin;

import com.vaka.daily_mvc.model.dto.UserDto;
import com.vaka.daily_mvc.service.UserService;
import com.vaka.daily_mvc.service.UserTypeService;
import com.vaka.daily_client.model.User;
import com.vaka.daily_client.model.UserType;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@Slf4j
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
    public String getById(@PathVariable("id") Integer id, Model model) {
        User user = userService.getById(id);

        model.addAttribute("user", user);

        return "admin/user/byId";
    }

    @GetMapping("/new")
    public String newUser(Model model) {
        model.addAttribute("user", new UserDto());

        return "redirect:/admin/user";
    }

    @PostMapping
    public String post(UserDto entity) {
        userService.create(new User());

        return "redirect:/admin/user";
    }

    @DeleteMapping
    public String delete(Integer id) {
        userService.deleteById(id);

        return "redirect:/admin/user";
    }

    @GetMapping("/edit/{id}")
    public String getEdit(@PathVariable("id") Integer id, Model model) {
        User user = userService.getById(id);

        List<String> userTypeNames = getUserTypeNames();

        model.addAttribute("user", user);
        model.addAttribute("userTypes", userTypeNames);

        return "admin/user/edit";
    }

    @PutMapping("/edit/{id}")
    public String put(Integer id, User entity) {
        userService.updateById(entity.getId(), entity);

        return "redirect:/admin/user";
    }

    private List<String> getUserTypeNames() {
        List<UserType> userTypes = userTypeService.getAll();

        return userTypes.stream()
                .map(UserType::getName)
                .map(StringUtils::capitalize)
                .toList();
    }
}
