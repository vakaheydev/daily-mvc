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
    public Client<Schedule> getClient() {
        return client;
    }
}
