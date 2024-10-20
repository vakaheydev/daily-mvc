package com.vaka.daily.controller.user;

import com.vaka.daily.model.converter.TaskConverter;
import com.vaka.daily.model.dto.TaskDto;
import com.vaka.daily.service.SimpleScheduleService;
import com.vaka.daily.service.TaskService;
import com.vaka.daily_client.model.Schedule;
import com.vaka.daily_client.model.Task;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.beans.PropertyEditorSupport;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static com.vaka.daily.model.converter.TaskConverter.*;

@Controller
@RequestMapping("/user/add")
@Slf4j
public class TaskController {
    TaskService taskService;
    SimpleScheduleService simpleScheduleService;

    public TaskController(TaskService taskService, SimpleScheduleService scheduleService) {
        this.taskService = taskService;
        this.simpleScheduleService = scheduleService;
    }

    @GetMapping
    public String addTask(Model model) {
        TaskDto task = new TaskDto();
        model.addAttribute("task", task);
        return "user/task/add";
    }

    @PostMapping
    public String saveTask(@ModelAttribute("task") TaskDto taskDto ) {
        Task task = convertToTask(taskDto);
        Schedule schedule = simpleScheduleService.getById(1);
        task.setSchedule(schedule);

        taskService.create(task);

        return "redirect:/user/start";
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.registerCustomEditor(LocalDateTime.class, new PropertyEditorSupport() {
            @Override
            public void setAsText(String text) throws IllegalArgumentException {
                // Определяет формат, который будет использоваться для конвертации
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");
                // Конвертирует строку в LocalDateTime
                setValue(LocalDateTime.parse(text, formatter));
            }
        });
    }

}
