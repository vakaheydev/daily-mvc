package com.vaka.daily.model.converter;

import com.vaka.daily.model.dto.UserDto;
import com.vaka.daily_client.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User source) {
        return new UserDto(source.getId(), source.getLogin(), source.getUserType());
    }
}
