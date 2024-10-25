package com.vaka.daily_mvc.service.authorization;

import com.vaka.daily_client.client.blocked.UserClient;
import com.vaka.daily_client.model.User;
import com.vaka.daily_client.model.UserNotFoundException;
import com.vaka.daily_client.model.dto.UserDTO;
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
    public boolean authorize(UserDTO userDTO) {
        if (userDTO.getLogin().isEmpty()) {
            return false;
        }
        User user = null;

        try {
            user = userClient.getByUniqueName(userDTO.getLogin());
        } catch (UserNotFoundException ignored) {
        }

        return user != null;
    }
}
