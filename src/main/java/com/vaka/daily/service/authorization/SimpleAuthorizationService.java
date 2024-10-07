package com.vaka.daily.service.authorization;

import com.vaka.dailyClient.client.blocked.UserClient;
import com.vaka.dailyClient.model.UserNotFoundException;
import com.vaka.dailyClient.model.User;
import com.vaka.dailyClient.model.dto.UserDTO;
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

//        try {
            user = userClient.getByUniqueName(userDTO.getLogin());
//        } catch (UserNotFoundException ignored) {
//        }

        return user != null;
    }
}
