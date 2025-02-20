package com.vaka.daily_mvc.service.authorization;

import com.vaka.daily_client.client.blocked.UserClient;
import com.vaka.daily_client.exception.notfound.UserNotFoundException;
import com.vaka.daily_client.model.User;
import com.vaka.daily_client.model.UserTypes;
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
    public boolean authorize(User user) {
        if (user.getLogin() == null || user.getPassword() == null) {
            return false;
        }

        if (user.getLogin().isEmpty() || user.getPassword().isEmpty()) {
            return false;
        }

        try {
            User byName = userClient.getByUniqueName(user.getLogin());
            return user.getPassword().equals(byName.getPassword());
        } catch (UserNotFoundException ignored) {
            return false;
        }
    }

    @Override
    public boolean existsUser(String login) {
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
