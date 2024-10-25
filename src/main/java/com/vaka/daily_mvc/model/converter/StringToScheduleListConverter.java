package com.vaka.daily_mvc.model.converter;

import com.vaka.daily_client.model.Schedule;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class StringToScheduleListConverter implements Converter<String, List<Schedule>> {
    @Override
    public List<Schedule> convert(String source) {
        List<Schedule> schedules = new ArrayList<>();

        if (source != null && !source.isEmpty()) {
            Schedule schedule = getSchedule(source);
            schedules.add(schedule);
        }

        return schedules;
    }

    private Schedule getSchedule(String text) {
        return null;
    }
}
