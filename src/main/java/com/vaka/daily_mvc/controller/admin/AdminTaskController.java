package com.vaka.daily_mvc.controller.admin;

import com.vaka.daily_mvc.service.TaskService;
import com.vaka.daily_client.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/task")
public class AdminTaskController implements AdminController<Task> {
    TaskService service;

    public AdminTaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    @Override
    public String get(Model model) {
        List<Task> tasks = service.getAll();
        model.addAttribute("tasks", tasks);

        return "/admin/task/index";
    }

    @GetMapping("/{id}")
    @Override
    public String getById(@PathVariable("id") Integer id, Model model) {
        Task task = service.getById(id);
        model.addAttribute("task", task);

        return "/admin/task/byId";
    }

    @Override
    public String put(Integer id, Task entity) {
        return null;
    }

    @Override
    public String post(Task entity) {
        return null;
    }

    @Override
    public String delete(Integer id) {
        return null;
    }
}
