package com.vaka.daily_mvc.controller.admin;

import com.vaka.daily_mvc.service.UserTypeService;
import com.vaka.daily_client.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/user_type")
public class AdminUserTypeController implements AdminController<UserType> {
    private UserTypeService userTypeService;

    @Autowired
    public AdminUserTypeController(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    @GetMapping
    public String get(Model model) {
        List<UserType> userTypes = userTypeService.getAll();
        model.addAttribute("userTypes", userTypes);
        return "admin/user_type/index";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Integer id, Model model) {
        UserType userType = userTypeService.getById(id);
        model.addAttribute("userType", userType);
        return "admin/user_type/byId";
    }

    @Override
    public String put(Integer id, UserType entity) {
        return null;
    }

    @Override
    public String post(UserType entity) {
        return null;
    }

    @Override
    public String delete(Integer id) {
        return null;
    }
}
