package com.vaka.daily_mvc.service;

import com.vaka.daily_client.client.Client;
import com.vaka.daily_client.client.blocked.UserClient;
import com.vaka.daily_client.exception.UserNotFoundException;
import com.vaka.daily_client.model.Schedule;
import com.vaka.daily_client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl extends AbstractService<User> implements UserService {
    UserClient userClient;
    PasswordEncoder encoder;

    @Autowired
    public UserServiceImpl(UserClient userClient, @Lazy PasswordEncoder encoder) {
        this.userClient = userClient;
        this.encoder = encoder;
    }

    @Override
    public List<User> getAll() {
        List<User> users = super.getAll();
        users.forEach(user -> setUserToSchedules(user.getSchedules(), user));

        return users;
    }

    @Override
    public User getById(Integer id) {
        User user = super.getById(id);
        setUserToSchedules(user.getSchedules(), user);

        return user;
    }

    @Override
    public boolean existsByUsername(String username) {
        try {
            userClient.getByUniqueName(username);
            return true;
        } catch (UserNotFoundException ignored) {
            return false;
        }
    }

    @Override
    public User getByUniqueName(String name) {
        User user = super.getByUniqueName(name);
        setUserToSchedules(user.getSchedules(), user);

        return user;
    }

    @Override
    public User updateById(Integer id, User entity) {
        if (entity.getSchedules() == null) {
            List<Schedule> schedules = userClient.getById(id).getSchedules();
            entity.setSchedules(schedules);
        }

        return super.updateById(id, entity);
    }

    @Override
    public User create(User entity) {
        entity.setPassword(encoder.encode(entity.getPassword()));
        return super.create(entity);
    }

    @Override
    public List<User> getByUserTypeName(String userTypeName) {
        return userClient.getByUserTypeName(userTypeName);
    }

    @Override
    public Client<User> getClient() {
        return userClient;
    }

    private void setUserToSchedules(List<Schedule> schedules, User user) {
        schedules.forEach(x -> x.setUser(user));
    }
}
