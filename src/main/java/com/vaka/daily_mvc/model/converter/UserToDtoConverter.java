package com.vaka.daily_mvc.model.converter;

import com.vaka.daily_mvc.model.dto.UserDto;
import com.vaka.daily_client.model.User;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class UserToDtoConverter implements Converter<User, UserDto> {
    @Override
    public UserDto convert(User source) {
        return UserDto.builder()
                .login(source.getLogin())
                .password(source.getPassword())
                .firstName(source.getFirstName())
                .secondName(source.getSecondName())
                .patronymic(source.getPatronymic())
                .userType(source.getUserType())
                .build();
    }
}
