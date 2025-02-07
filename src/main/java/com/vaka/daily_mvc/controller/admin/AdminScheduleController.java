package com.vaka.daily_mvc.controller.admin;

import com.vaka.daily_client.model.Schedule;
import com.vaka.daily_client.model.Task;
import com.vaka.daily_client.model.User;
import com.vaka.daily_mvc.service.ScheduleService;
import com.vaka.daily_mvc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin/schedule")
@SessionAttributes({"userId", "dbSchedule"})
public class AdminScheduleController {
    UserService userService;
    ScheduleService scheduleService;

    public AdminScheduleController(UserService userService, ScheduleService scheduleService) {
        this.userService = userService;
        this.scheduleService = scheduleService;
    }

    @GetMapping
    public String get(Model model) {
        List<Schedule> schedules = scheduleService.getAll().stream().toList();

        model.addAttribute("schedules", schedules);
        return "admin/schedule/index";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") Integer id, Model model) {
        Schedule schedule = scheduleService.getById(id);

        model.addAttribute("schedule", schedule);
        return "admin/schedule/byId";
    }

    @GetMapping("/edit/{id}")
    public String getPut(@PathVariable("id") Integer id, Model model) {
        Schedule schedule = scheduleService.getById(id);
        model.addAttribute("schedule", schedule);
        model.addAttribute("dbSchedule", schedule);

        return "admin/schedule/edit";
    }

    @PutMapping("/edit/{id}")
    public String put(@ModelAttribute("dbSchedule") Schedule dbSchedule, @PathVariable("id") Integer id,
                      Schedule entity) {
        entity.setUser(new User(dbSchedule.getUser().getId()));
        entity.setTasks(dbSchedule.getTasks());
        scheduleService.updateById(id, entity);

        return "redirect:/admin/schedule";
    }

    @GetMapping("/new")
    public String getPost(@RequestParam("userId") Integer userId, Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("userId", userId);

        return "admin/schedule/new";
    }

    @PostMapping("/new")
    public String post(@ModelAttribute("userId") Integer userId, Schedule entity) {
        User user = userService.getById(userId);
        entity.setUser(user);
        scheduleService.create(entity);

        return "redirect:/admin/schedule";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        List<Task> tasks = scheduleService.getById(id).getTasks();

        if (!tasks.isEmpty()) {
            throw new RuntimeException(tasks.size() + " tasks have this schedule");
        }

        scheduleService.deleteById(id);

        return "redirect:/admin/schedule";
    }
}
