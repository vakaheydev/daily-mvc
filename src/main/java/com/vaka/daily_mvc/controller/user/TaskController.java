package com.vaka.daily_mvc.controller.user;

import com.vaka.daily_mvc.model.dto.TaskDto;
import com.vaka.daily_mvc.service.TaskService;
import com.vaka.daily_client.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/user/task")
@Slf4j
@SessionAttributes("scheduleId")
public class TaskController {
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/add")
    public String addTask(Model model, @RequestParam("scheduleId") int scheduleId) {
        TaskDto task = new TaskDto();

        model.addAttribute("task", task);
        model.addAttribute("scheduleId", scheduleId);
        return "user/task/add";
    }

    @PostMapping("/add")
    public String saveTask(Model model, @ModelAttribute("task") Task task) {
        Integer scheduleId = (Integer) model.getAttribute("scheduleId");

        task.setScheduleId(scheduleId);
        taskService.create(task);

        return "redirect:/user/start";
    }

    @GetMapping("/edit/{id}")
    public String getEditTask(Model model, @PathVariable("id") Integer id, @RequestParam("scheduleId") int scheduleId) {
        Task task = taskService.getById(id);
        model.addAttribute("task", task);
        model.addAttribute("deadline", task.getDeadline());

        return "user/task/edit";
    }

    @PutMapping("/edit/{id}")
    public String putEditTask(@PathVariable("id") Integer id, Task task, @RequestParam("scheduleId") int scheduleId) {
        task.setScheduleId(scheduleId);
        taskService.updateById(id, task);

        return "redirect:/user/start";
    }

    @DeleteMapping({"/delete/{id}"})
    public String deleteTask(@PathVariable("id") Integer id) {
        taskService.deleteById(id);

        return "redirect:/user/start";
    }
}
