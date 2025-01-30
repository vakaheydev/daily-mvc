package com.vaka.daily_mvc.service;

import com.vaka.daily_client.client.Client;
import com.vaka.daily_client.client.blocked.ScheduleClient;
import com.vaka.daily_client.model.Schedule;
import org.springframework.stereotype.Service;

@Service
public class SimpleScheduleService extends AbstractService<Schedule> implements ScheduleService {
    ScheduleClient client;

    public SimpleScheduleService(ScheduleClient client) {
        this.client = client;
    }

    @Override
    public Schedule updateById(Integer id, Schedule entity) {
        if (entity.getName().equals("main")) {
            throw new UnsupportedOperationException("Main schedule can't be updated");
        }

        return super.updateById(id, entity);
    }

    @Override
    public void deleteById(Integer id) {
        Schedule schedule = client.getById(id);

        if (schedule.getName().equals("main")) {
            throw new UnsupportedOperationException("Main schedule can't be deleted");
        }

        super.deleteById(id);
    }

    @Override
    public Client<Schedule> getClient() {
        return client;
    }
}
