package com.vaka.daily_mvc.model.converter;

import com.vaka.daily_mvc.model.dto.ScheduleDto;
import com.vaka.daily_client.model.Schedule;
import com.vaka.daily_mvc.model.dto.UserDto;
import com.vaka.daily_mvc.service.UserService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class ScheduleToDtoConverter implements Converter<Schedule, ScheduleDto> {
    UserToDtoConverter userToDtoConverter;
    UserService userService;

    public ScheduleToDtoConverter(UserToDtoConverter userToDtoConverter, UserService userService) {
        this.userToDtoConverter = userToDtoConverter;
        this.userService = userService;
    }

    @Override
    public ScheduleDto convert(Schedule source) {
        UserDto userDto = userToDtoConverter.convert(source.getUser());

        if (source.getUser().getLogin() == null) {
            Integer userId = source.getUser().getId();
            userDto = userToDtoConverter.convert(userService.getById(userId));

        }
        return new ScheduleDto(source.getId(), source.getName(), userDto,
                source.getTasks());
    }
}
