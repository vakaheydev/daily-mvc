package com.vaka.daily_mvc.service;

import com.vaka.daily_client.client.Client;
import com.vaka.daily_client.client.blocked.UserClient;
import com.vaka.daily_client.model.Schedule;
import com.vaka.daily_client.model.User;
import com.vaka.daily_mvc.model.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SimpleUserService extends AbstractService<User> implements UserService {
    UserClient userClient;

    @Autowired
    public SimpleUserService(UserClient userClient) {
        this.userClient = userClient;
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
    public User create(UserDto entity) {
        User user = User.builder()
                .login(entity.getLogin())
                .password(entity.getPassword())
                .firstName(entity.getFirstName())
                .secondName(entity.getSecondName())
                .patronymic(entity.getPatronymic())
                .userType(entity.getUserType())
                .build();

        return userClient.create(user);
    }

    @Override
    public List<User> getByUserTypeName(String userTypeName) {
        return userClient.getByUserTypeName(userTypeName);
    }

    @Override
    public Client<User> getClient() {
        return userClient;
    }
}
