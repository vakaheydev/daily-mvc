package com.vaka.daily.service;

import com.vaka.daily.model.dto.ScheduleDto;
import com.vaka.daily_client.model.Schedule;

public interface ScheduleService extends CommonService<Schedule> {
    ScheduleDto toDto(Schedule schedule);
}
