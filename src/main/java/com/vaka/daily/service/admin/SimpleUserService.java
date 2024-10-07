package com.vaka.daily.service.admin;

import com.vaka.daily.service.AbstractService;
import com.vaka.dailyClient.client.Client;
import com.vaka.dailyClient.client.blocked.UserClient;
import com.vaka.dailyClient.model.Schedule;
import com.vaka.dailyClient.model.User;
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
