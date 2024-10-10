package com.vaka.daily.controller.admin;

import com.vaka.daily.controller.CommonController;
import com.vaka.daily.model.dto.ScheduleDto;
import com.vaka.daily.service.admin.ScheduleService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin/schedule")
public class AdminScheduleController implements CommonController {
    ScheduleService service;

    public AdminScheduleController(ScheduleService service) {
        this.service = service;
    }

    @GetMapping
    @Override
    public String get(Model model) {
        List<ScheduleDto> scheduleDtos = service.convertToDto(service.getAll());

        model.addAttribute("schedules", scheduleDtos);
        return "admin/schedule/index";
    }

    @GetMapping("/{id}")
    @Override
    public String getById(Integer id, Model model) {
        return null;
    }
}
