package com.vaka.daily_mvc.model.converter;

import com.vaka.daily_mvc.model.dto.ScheduleDto;
import com.vaka.daily_client.model.Schedule;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ScheduleToDtoConverter implements Converter<Schedule, ScheduleDto> {
    UserToDtoConverter userToDtoConverter;

    public ScheduleToDtoConverter(UserToDtoConverter userToDtoConverter) {
        this.userToDtoConverter = userToDtoConverter;
    }

    @Override
    public ScheduleDto convert(Schedule source) {
        return new ScheduleDto(source.getId(), source.getName(), userToDtoConverter.convert(source.getUser()),
                source.getTasks());
    }
}
