package com.vaka.daily_mvc.controller.admin;

import com.vaka.daily_client.model.Task;
import com.vaka.daily_client.model.TaskType;
import com.vaka.daily_mvc.model.dto.TaskDto;
import com.vaka.daily_mvc.service.TaskService;
import com.vaka.daily_mvc.service.TaskTypeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/admin/task")
@SessionAttributes("scheduleId")
public class AdminTaskController {
    TaskService taskService;
    TaskTypeService taskTypeService;

    public AdminTaskController(TaskService taskService, TaskTypeService taskTypeService) {
        this.taskService = taskService;
        this.taskTypeService = taskTypeService;
    }

    @GetMapping
    public String get(Model model) {
        List<Task> tasks = taskService.getAll();
        model.addAttribute("tasks", tasks);

        return "admin/task/index";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Integer id, Model model) {
        Task task = taskService.getById(id);
        model.addAttribute("task", task);

        return "admin/task/byId";
    }

    @GetMapping("/edit/{id}")
    public String getPut(Model model, @PathVariable("id") Integer taskId) {
        Task task = taskService.getById(taskId);
        List<TaskType> taskTypes = taskTypeService.getAll();

        model.addAttribute("task", task);
        model.addAttribute("scheduleId", task.getScheduleId());
        model.addAttribute("deadline", task.getDeadline());
        model.addAttribute("taskTypes", taskTypes);

        return "admin/task/edit";
    }

    @PutMapping("/edit/{id}")
    public String put(@PathVariable("id") Integer id, Task entity, @ModelAttribute("scheduleId") int scheduleId) {
        entity.setScheduleId(scheduleId);
        taskService.updateById(id, entity);

        return "redirect:/admin/schedule/" + scheduleId;
    }

    @GetMapping("/new")
    public String getPost(Model model, @RequestParam("scheduleId") int scheduleId) {
        TaskDto task = new TaskDto();
        List<TaskType> taskTypes = taskTypeService.getAll();

        model.addAttribute("task", task);
        model.addAttribute("scheduleId", scheduleId);
        model.addAttribute("taskTypes", taskTypes);

        return "admin/task/add";
    }

    @PostMapping("/new")
    public String post(Task entity, @ModelAttribute("scheduleId") Integer scheduleId) {
        entity.setScheduleId(scheduleId);
        taskService.create(entity);

        return "redirect:/admin/schedule/" + scheduleId;
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        taskService.deleteById(id);

        return "redirect:/admin/schedule";
    }
}
