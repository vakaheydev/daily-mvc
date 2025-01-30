package com.vaka.daily_mvc.controller.user;

import com.vaka.daily_client.exception.ScheduleNotFoundException;
import com.vaka.daily_client.exception.UserNotFoundException;
import com.vaka.daily_client.model.Schedule;
import com.vaka.daily_client.model.Task;
import com.vaka.daily_mvc.service.UserService;
import com.vaka.daily_client.model.User;
import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.security.SecurityConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/start")
    public String getStart(@RequestParam(required = false, name = "scheduleId") Integer scheduleId, Model model) {
        SecurityContext securityCtx = SecurityContextHolder.getContext();
        Authentication authentication = securityCtx.getAuthentication();
        String username = authentication.getName();
        User user = userService.getByUniqueName(username);

        Schedule schedule;

        if (scheduleId == null) {
            schedule = user.getSchedules().stream()
                    .filter(x -> x.getName().equals("main"))
                    .findFirst()
                    .orElseThrow(() -> new ScheduleNotFoundException("main"));
        } else {
            schedule = user.getSchedules().stream()
                    .filter(x -> x.getId().equals(scheduleId))
                    .findFirst()
                    .orElseThrow(() -> new ScheduleNotFoundException(scheduleId));
        }

        schedule.setTasks(schedule.getTasks().stream()
                .sorted(Comparator.comparingInt(Task::getId))
                .toList()
        );

        model.addAttribute("user", user);
        model.addAttribute("schedule", schedule);

        List<Task> completedTask = schedule.getTasks().stream()
                .filter(Task::getStatus)
                .toList();

        List<Task> uncompletedTask = schedule.getTasks().stream()
                .filter(x -> !x.getStatus())
                .toList();

        model.addAttribute("uncompleted", uncompletedTask);
        model.addAttribute("completed", completedTask);

        return "user/start";
    }
}
