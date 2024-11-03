package com.vaka.daily_mvc.controller.admin;

import com.vaka.daily_mvc.service.UserService;
import com.vaka.daily_mvc.service.UserTypeService;
import com.vaka.daily_client.model.UserType;
import com.vaka.daily_client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/admin/user_type")
public class AdminUserTypeController implements AdminController<UserType> {
    private UserTypeService userTypeService;
    private UserService userService;

    public AdminUserTypeController(UserTypeService userTypeService, UserService userService) {
        this.userTypeService = userTypeService;
        this.userService = userService;
    }

    @GetMapping
    public String get(Model model) {
        List<UserType> userTypes = userTypeService.getAll();
        userTypes.sort(Comparator.comparingInt(UserType::getId));
        model.addAttribute("userTypes", userTypes);
        return "admin/user_type/index";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Integer id, Model model) {
        UserType userType = userTypeService.getById(id);
        List<User> users = userService.getByUserTypeName(userType.getName());

        model.addAttribute("userType", userType);
        model.addAttribute("users", users);
        return "admin/user_type/byId";
    }

    @GetMapping("/edit/{id}")
    public String getPut(@PathVariable("id") Integer id, Model model) {
        UserType userType = userTypeService.getById(id);
        model.addAttribute("userType", userType);

        return "/admin/user_type/edit";
    }

    @PutMapping("/edit/{id}")
    @Override
    public String put(@PathVariable("id") Integer id, UserType entity) {
        userTypeService.updateById(id, entity);

        return "redirect:/admin/user_type";
    }

    @GetMapping("/new")
    public String getPost(Model model) {
        model.addAttribute("userType", new UserType());

        return "admin/user_type/new";
    }

    @PostMapping("/new")
    @Override
    public String post(UserType entity) {
        userTypeService.create(entity);
        return "redirect:/admin/user_type";
    }

    @DeleteMapping("/delete/{id}")
    @Override
    public String delete(@PathVariable("id") Integer id) {
        String userTypeName = userTypeService.getById(id).getName();
        List<User> users = userService.getByUserTypeName(userTypeName);

        if (!users.isEmpty()) {
            throw new RuntimeException(users.size() + " user(-s) have this user type");
        }

        userTypeService.deleteById(id);

        return "redirect:/admin/user_type";
    }
}
