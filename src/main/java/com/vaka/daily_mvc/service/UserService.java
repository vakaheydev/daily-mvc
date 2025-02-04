package com.vaka.daily_mvc.service;

import com.vaka.daily_client.model.User;

import java.util.List;

public interface UserService extends CommonService<User> {
    List<User> getByUserTypeName(String userTypeName);
    boolean existsByUsername(String username);
}
