package com.vaka.daily.service.admin;

import com.vaka.daily.service.AbstractService;
import com.vaka.dailyClient.client.Client;
import com.vaka.dailyClient.client.blocked.ScheduleClient;
import com.vaka.dailyClient.model.Schedule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleScheduleService extends AbstractService<Schedule> implements ScheduleService {
    ScheduleClient client;

    @Autowired
    public SimpleScheduleService(ScheduleClient client) {
        this.client = client;
    }

    @Override
    public Client<Schedule> getClient() {
        return client;
    }
}
