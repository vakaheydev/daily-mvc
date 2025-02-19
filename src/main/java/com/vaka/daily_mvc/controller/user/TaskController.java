package com.vaka.daily_mvc.controller.user;

import com.vaka.daily_client.model.Schedule;
import com.vaka.daily_client.model.TaskType;
import com.vaka.daily_mvc.model.dto.TaskDto;
import com.vaka.daily_mvc.service.ScheduleService;
import com.vaka.daily_mvc.service.TaskService;
import com.vaka.daily_client.model.Task;
import com.vaka.daily_mvc.service.TaskTypeService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/user/task")
@Slf4j
@SessionAttributes("scheduleId")
public class TaskController {
    private final TaskService taskService;
    private final TaskTypeService taskTypeService;
    private final ScheduleService scheduleService;

    public TaskController(TaskService taskService, TaskTypeService taskTypeService, ScheduleService scheduleService) {
        this.taskService = taskService;
        this.taskTypeService = taskTypeService;
        this.scheduleService = scheduleService;
    }

    @GetMapping("/add")
    public String addTask(Model model, @RequestParam("scheduleId") int scheduleId) {
        TaskDto task = new TaskDto();
        List<TaskType> taskTypes = taskTypeService.getAll();

        model.addAttribute("task", task);
        model.addAttribute("scheduleId", scheduleId);
        model.addAttribute("taskTypes", taskTypes);

        return "user/task/add";
    }

    @PostMapping("/add")
    public String saveTask(Model model,
                           @Valid @ModelAttribute("task") Task task,
                           BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "user/task/add";
        }

        task.setStatus(false);

        Integer scheduleId = (Integer) model.getAttribute("scheduleId");

        Schedule schedule = scheduleService.getById(scheduleId); // TODO: 2/11/2025 Check if specified schedule user is current authenticated user

        task.setScheduleId(scheduleId);
        taskService.create(task);

        return "redirect:/user/start?scheduleId=" + task.getScheduleId();
    }

    @GetMapping("/edit/{id}")
    public String getEditTask(Model model, @PathVariable("id") Integer id, @RequestParam("scheduleId") int scheduleId) {
        Task task = taskService.getById(id);
        List<TaskType> taskTypes = taskTypeService.getAll();

        model.addAttribute("task", task);
        model.addAttribute("deadline", task.getDeadline());
        model.addAttribute("taskTypes", taskTypes);

        return "user/task/edit";
    }

    @PatchMapping("/done/{id}")
    public String patchDoneTask(@PathVariable("id") Integer id) {
        Task task = taskService.getById(id);
        task.setStatus(true);

        taskService.updateById(id, task);

        return "redirect:/user/start?scheduleId=" + task.getScheduleId();
    }

    @PutMapping("/edit/{id}")
    public String putEditTask(@PathVariable("id") Integer id, Task task, @RequestParam("scheduleId") int scheduleId) {
        task.setScheduleId(scheduleId);
        taskService.updateById(id, task);

        return "redirect:/user/start?scheduleId=" + scheduleId;
    }

    @DeleteMapping({"/delete/{id}"})
    public String deleteTask(@PathVariable("id") Integer id) {
        Integer scheduleId = taskService.getById(id).getScheduleId();
        taskService.deleteById(id);

        return "redirect:/user/start?scheduleId=" + scheduleId;
    }
}
