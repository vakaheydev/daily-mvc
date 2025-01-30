package com.vaka.daily_mvc.security;

import com.vaka.daily_client.exception.UserNotFoundException;
import com.vaka.daily_client.model.User;
import com.vaka.daily_mvc.service.UserService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

public class UserDetailsServiceImpl implements UserDetailsService {
    UserService userService;

    public UserDetailsServiceImpl(UserService userService) {
        this.userService = userService;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user;

        try {
            user = userService.getByUniqueName(username);
        } catch (UserNotFoundException ex) {
            throw new UsernameNotFoundException(String.format("User with username %s wasn't found", username));
        }

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                new ArrayList<>());
    }
}
