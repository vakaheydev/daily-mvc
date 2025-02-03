package com.vaka.daily_mvc.service.authorization;

import com.vaka.daily_client.model.User;
import com.vaka.daily_client.model.UserTypes;

public interface AuthorizationService {
    boolean authorize(User user);
    boolean existsUser(String login);

    boolean hasRole(String login, UserTypes userType);
}
