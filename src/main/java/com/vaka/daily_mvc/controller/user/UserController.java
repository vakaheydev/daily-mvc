package com.vaka.daily_mvc.controller.user;

import com.vaka.daily_client.exception.ScheduleNotFoundException;
import com.vaka.daily_client.exception.UserNotFoundException;
import com.vaka.daily_client.model.Schedule;
import com.vaka.daily_mvc.service.UserService;
import com.vaka.daily_client.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
    public String getStart(@RequestParam(required = false, name = "scheduleName", defaultValue = "main") String scheduleName, @CookieValue(value = "username", defaultValue = "") String username, Model model) {
        if (username.equals(" ")) {
            return "redirect:authorization/login";
        }

        User user = userService.getByUniqueName(username);

        Schedule schedule = user.getSchedules().stream()
                .filter(x -> x.getName().equals(scheduleName))
                .findFirst()
                .orElseThrow(() -> new ScheduleNotFoundException(scheduleName));

        model.addAttribute("user", user);
        model.addAttribute("schedule", schedule);

        return "user/start";
    }

    @ExceptionHandler(UserNotFoundException.class)
    public String handleUserNotFoundException(UserNotFoundException e) {
        log.error("User not found: {}", e.getMessage());

        return "redirect:/start";
    }
}
