package com.vaka.daily.service.authorization;

import com.vaka.daily_client.model.dto.UserDTO;

public interface AuthorizationService {
    boolean authorize(UserDTO userDTO);
}
