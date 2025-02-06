package com.vaka.daily_mvc.controller.user;

import com.vaka.daily_client.exception.ValidationException;
import com.vaka.daily_client.model.User;
import com.vaka.daily_mvc.service.TelegramBindingService;
import com.vaka.daily_mvc.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user/tg")
public class UserTelegramBindingController {
    private final UserService userService;
    private final TelegramBindingService telegramBindingService;

    @Autowired
    public UserTelegramBindingController(UserService userService, TelegramBindingService telegramBindingService) {
        this.userService = userService;
        this.telegramBindingService = telegramBindingService;
    }

    @GetMapping("/bind")
    public String bindTg() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();
        Integer userId = userService.getByUniqueName(username).getId();
        String token;

        try {
            token = telegramBindingService.createToken(userId);
        } catch (ValidationException ex) {
            token = telegramBindingService.getTokenByUserId(userId);
        }

        return "redirect:https://t.me/vaka_daily_bot?start=" + token;
    }

    @DeleteMapping("/bind")
    public String unbindTg() {
        String username = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userService.getByUniqueName(username);
        user.setTelegramId(null);
        userService.updateById(user.getId(), user);

        return "redirect:/start";
    }
}
