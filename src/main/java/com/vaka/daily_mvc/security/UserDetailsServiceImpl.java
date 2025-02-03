package com.vaka.daily_mvc.security;

import com.vaka.daily_client.exception.UserNotFoundException;
import com.vaka.daily_client.model.User;
import com.vaka.daily_mvc.service.UserService;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;

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

        String role = "ROLE_" + user.getUserType().getName().toUpperCase();

        return new org.springframework.security.core.userdetails.User(user.getLogin(), user.getPassword(),
                List.of(new SimpleGrantedAuthority(role)));
    }
}
