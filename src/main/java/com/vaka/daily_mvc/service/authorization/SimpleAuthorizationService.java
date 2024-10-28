package com.vaka.daily_mvc.service.authorization;

import com.vaka.daily_client.client.blocked.UserClient;
import com.vaka.daily_client.exception.UserNotFoundException;
import com.vaka.daily_client.model.User;
import com.vaka.daily_client.model.UserTypes;
import com.vaka.daily_mvc.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleAuthorizationService implements AuthorizationService {
    private final UserClient userClient;

    @Autowired
    public SimpleAuthorizationService(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public boolean authorize(UserDto userDto) {
        if (userDto.getLogin() == null || userDto.getPassword() == null) {
            return false;
        }

        if (userDto.getLogin().isEmpty() || userDto.getPassword().isEmpty()) {
            return false;
        }

        try {
            User user = userClient.getByUniqueName(userDto.getLogin());
            return user.getPassword().equals(userDto.getPassword());
        } catch (UserNotFoundException ignored) {
            return false;
        }
    }

    @Override
    public boolean checkUsername(String login) {
        if (login == null || login.isEmpty()) {
            return false;
        }

        try {
            User user = userClient.getByUniqueName(login);
            return user != null;
        } catch (UserNotFoundException ignored) {
            return false;
        }
    }

    @Override
    public boolean hasRole(String login, UserTypes userType) {
        if (login == null || login.isEmpty()) {
            return false;
        }

        try {
            User user = userClient.getByUniqueName(login);

            if (user == null) {
                return false;
            }

            return UserTypes.valueOf(user.getUserType().getName().toUpperCase()).compareTo(userType) >= 0;
        } catch (UserNotFoundException ignored) {
            return false;
        }
    }
}
