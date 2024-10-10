package com.vaka.daily.service.admin;

import com.vaka.daily.service.AbstractService;
import com.vaka.daily_client.client.Client;
import com.vaka.daily_client.client.blocked.UserClient;
import com.vaka.daily_client.model.Schedule;
import com.vaka.daily_client.model.User;
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
    public Client<User> getClient() {
        return userClient;
    }
}
