package com.vaka.daily_mvc.service;

import com.vaka.daily_mvc.model.dto.UserDto;
import com.vaka.daily_client.model.User;

public interface UserService extends CommonService<User> {
    User create(UserDto entity);
}
