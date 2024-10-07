package com.vaka.daily.service.authorization;

import com.vaka.dailyClient.model.dto.UserDTO;

public interface AuthorizationService {
    boolean authorize(UserDTO userDTO);
}
