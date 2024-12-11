package com.vaka.daily_mvc.controller.admin;

import com.vaka.daily_mvc.model.dto.TaskDto;
import com.vaka.daily_mvc.service.TaskService;
import com.vaka.daily_client.model.Task;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/task")
@SessionAttributes("scheduleId")
public class AdminTaskController {
    TaskService service;

    public AdminTaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping
    public String get(Model model) {
        List<Task> tasks = service.getAll();
        model.addAttribute("tasks", tasks);

        return "/admin/task/index";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Integer id, Model model) {
        Task task = service.getById(id);
        model.addAttribute("task", task);

        return "/admin/task/byId";
    }

    @GetMapping("/edit/{id}")
    public String getPut(Model model, @PathVariable("id") Integer taskId) {
        Task task = service.getById(taskId);

        model.addAttribute("task", task);
        model.addAttribute("scheduleId", task.getScheduleId());
        model.addAttribute("deadline", task.getDeadline());

        return "/admin/task/edit";
    }

    @PutMapping("/edit/{id}")
    public String put(@PathVariable("id") Integer id, Task entity, @ModelAttribute("scheduleId") int scheduleId) {
        entity.setScheduleId(scheduleId);
        service.updateById(id, entity);

        return "redirect:/admin/schedule/" + scheduleId;
    }

    @GetMapping("/new")
    public String getPost(Model model, @RequestParam("scheduleId") int scheduleId) {
        TaskDto task = new TaskDto();

        model.addAttribute("task", task);
        model.addAttribute("scheduleId", scheduleId);
        return "/admin/task/add";
    }

    @PostMapping("/new")
    public String post(Task entity, @ModelAttribute("scheduleId") Integer scheduleId) {
        entity.setScheduleId(scheduleId);
        service.create(entity);

        return "redirect:/admin/schedule/" + scheduleId;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        service.deleteById(id);

        return "redirect:/admin/schedule";
    }
}
