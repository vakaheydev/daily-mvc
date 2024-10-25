package com.vaka.daily_mvc.controller.admin;

import com.vaka.daily_mvc.model.dto.ScheduleDto;
import com.vaka.daily_mvc.service.ScheduleService;
import com.vaka.daily_client.model.Schedule;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@Slf4j
@RequestMapping("/admin/schedule")
public class AdminScheduleController implements AdminController<Schedule> {
    ScheduleService service;

    public AdminScheduleController(ScheduleService service) {
        this.service = service;
    }

    @GetMapping
    @Override
    public String get(Model model) {
        List<ScheduleDto> scheduleDtos = service.getAll().stream()
                .map(service::toDto)
                .toList();

        model.addAttribute("schedules", scheduleDtos);
        return "admin/schedule/index";
    }

    @GetMapping("/{id}")
    @Override
    public String getById(@PathVariable("id") Integer id, Model model) {
        ScheduleDto schedule = service.toDto(service.getById(id));

        model.addAttribute("schedule", schedule);
        return "admin/schedule/byId";
    }

    @Override
    public String put(Integer id, Schedule entity) {
        return null;
    }

    @Override
    public String post(Schedule entity) {
        return null;
    }

    @Override
    public String delete(Integer id) {
        return null;
    }
}
