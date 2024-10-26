package com.vaka.daily_mvc.service.authorization;

import com.vaka.daily_client.model.UserType;
import com.vaka.daily_client.model.UserTypes;
import com.vaka.daily_mvc.model.dto.UserDto;

public interface AuthorizationService {
    boolean authorize(UserDto userDto);
    boolean checkUsername(String login);

    boolean hasRole(String login, UserTypes userType);
}
