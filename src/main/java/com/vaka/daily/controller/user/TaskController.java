package com.vaka.daily.controller.user;

import com.vaka.daily.model.dto.TaskDto;
import com.vaka.daily.service.ScheduleService;
import com.vaka.daily.service.TaskService;
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
    ScheduleService scheduleService;

    public TaskController(TaskService taskService, ScheduleService scheduleService) {
        this.taskService = taskService;
        this.scheduleService = scheduleService;
    }

    @GetMapping("/add")
    public String addTask(Model model, @RequestParam("scheduleId") int scheduleId) {
        TaskDto task = new TaskDto();
        log.info("ScheduleId: {}", scheduleId);

        model.addAttribute("task", task);
        model.addAttribute("scheduleId", scheduleId);
        return "user/task/add";
    }

    @PostMapping("/add")
    public String saveTask(Model model, @ModelAttribute("task") Task task) {
        Integer scheduleId = (Integer) model.getAttribute("scheduleId");
        log.info("ScheduleId: {}", scheduleId);

        task.setScheduleId(scheduleId);
        taskService.create(task);

        return "redirect:/user/start";
    }

    @PutMapping("/edit")
    public String editTask() {
        throw new RuntimeException("Aziz sdelai etot method");
    }

    @DeleteMapping({"/delete"})
    public String deleteTask() {
        throw new RuntimeException("Aziz sdelai etot method");
    }
}
