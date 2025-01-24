package com.vaka.daily_mvc.controller.user;

import com.vaka.daily_client.model.Schedule;
import com.vaka.daily_client.model.Task;
import com.vaka.daily_client.model.User;
import com.vaka.daily_mvc.service.ScheduleService;
import com.vaka.daily_mvc.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/user/schedule")
@Controller
@SessionAttributes({"userId", "dbSchedule"})
public class ScheduleController {
    ScheduleService scheduleService;
    UserService userService;

    public ScheduleController(ScheduleService scheduleService, UserService userService) {
        this.scheduleService = scheduleService;
        this.userService = userService;
    }

    @GetMapping("/{id}")
    public String get(@PathVariable("id") Integer id, Model model) {
        Schedule schedule = scheduleService.getById(id);
        model.addAttribute("schedule", schedule);

        return "user/schedule/byId";
    }

    @GetMapping("/edit/{id}")
    public String getPut(@PathVariable("id") Integer id, Model model) {
        Schedule schedule = scheduleService.getById(id);
        model.addAttribute("schedule", schedule);
        model.addAttribute("dbSchedule", schedule);

        return "user/schedule/edit";
    }

    @PutMapping("/edit/{id}")
    public String put(@ModelAttribute("dbSchedule") Schedule dbSchedule, @PathVariable("id") Integer id,
                      Schedule entity) {
        entity.setUser(new User(dbSchedule.getUser().getId()));
        entity.setTasks(dbSchedule.getTasks());
        scheduleService.updateById(id, entity);

        return "redirect:/user/start";
    }

    @GetMapping("/new")
    public String getPost(@RequestParam("userId") Integer userId, Model model) {
        model.addAttribute("schedule", new Schedule());
        model.addAttribute("userId", userId);

        return "user/schedule/new";
    }

    @PostMapping("/new")
    public String post(@ModelAttribute("userId") Integer userId, Schedule entity) {
        User user = userService.getById(userId);
        entity.setUser(user);
        scheduleService.create(entity);

        return "redirect:/user/start";
    }

    @DeleteMapping("/delete/{id}")
    public String delete(@PathVariable("id") Integer id) {
        List<Task> tasks = scheduleService.getById(id).getTasks();

        if (!tasks.isEmpty()) {
            throw new RuntimeException(tasks.size() + " tasks have this schedule");
        }

        scheduleService.deleteById(id);

        return "redirect:/user/start";
    }
}
