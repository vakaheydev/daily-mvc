package com.vaka.daily_mvc.controller.user;

import com.vaka.daily_client.exception.notfound.ScheduleNotFoundException;
import com.vaka.daily_client.exception.notfound.UserNotFoundException;
import com.vaka.daily_client.model.Schedule;
import com.vaka.daily_client.model.Task;
import com.vaka.daily_client.model.User;
import com.vaka.daily_mvc.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Comparator;
import java.util.List;

@Controller
@RequestMapping("/user")
@Slf4j
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/start")
    public String getStart(@RequestParam(required = false, name = "scheduleId") Integer scheduleId, Model model,
                           HttpServletRequest request, HttpServletResponse response) {
        SecurityContext securityCtx = SecurityContextHolder.getContext();
        Authentication authentication = securityCtx.getAuthentication();
        String username = authentication.getName();

        User user;

        try {
            user = userService.getByUniqueName(username);
        } catch (UserNotFoundException ex) {
            new SecurityContextLogoutHandler().logout(request, response, authentication);
            return "redirect:/login?logout";
        }

        Schedule schedule;

        if (scheduleId == null) {
            schedule = user.getSchedules().stream()
                    .filter(x -> x.getName().equals("main"))
                    .findFirst()
                    .orElseThrow(() -> new ScheduleNotFoundException("name", "main"));
        } else {
            schedule = user.getSchedules().stream()
                    .filter(x -> x.getId().equals(scheduleId))
                    .findFirst()
                    .orElseThrow(() -> new ScheduleNotFoundException("id", scheduleId));
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
        model.addAttribute("tgId", user.getTelegramId());

        return "user/start";
    }
}
