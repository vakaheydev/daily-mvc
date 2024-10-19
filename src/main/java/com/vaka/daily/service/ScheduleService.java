package com.vaka.daily.service;

import com.vaka.daily.model.dto.ScheduleDto;
import com.vaka.daily.service.CommonService;
import com.vaka.daily_client.model.Schedule;

import java.util.List;

public interface ScheduleService extends CommonService<Schedule> {
    ScheduleDto toDto(Schedule schedule);
}
