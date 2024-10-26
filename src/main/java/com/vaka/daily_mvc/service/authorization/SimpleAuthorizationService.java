package com.vaka.daily_mvc.service.authorization;

import com.vaka.daily_client.client.blocked.UserClient;
import com.vaka.daily_client.model.User;
import com.vaka.daily_client.model.UserNotFoundException;
import com.vaka.daily_client.model.dto.UserDTO;
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
    public boolean checkUsername(String username) {
        if (username == null || username.isEmpty()) {
            return false;
        }

        try {
            User user = userClient.getByUniqueName(username);
            return user != null;
        } catch (UserNotFoundException ignored) {
            return false;
        }
    }
}
