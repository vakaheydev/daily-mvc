package com.vaka.daily.controller.user;

import com.vaka.daily.service.TaskService;
import com.vaka.daily_client.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/add")
@Slf4j
public class TaskController {
    TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping
    public String getTaskAdd() {
        return "user/task/add";
    }

    @PostMapping
    public String postTaskAdd(Task task) {
        log.info("Task: {}", task);

        return "user/start";
    }
}
