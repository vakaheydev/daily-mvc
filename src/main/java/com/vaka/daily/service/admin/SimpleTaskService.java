package com.vaka.daily.service.admin;

import com.vaka.daily.service.AbstractService;
import com.vaka.daily_client.client.Client;
import com.vaka.daily_client.client.blocked.TaskClient;
import com.vaka.daily_client.model.Task;
import org.springframework.stereotype.Service;

@Service
public class SimpleTaskService extends AbstractService<Task> implements TaskService {
    TaskClient client;

    public SimpleTaskService(TaskClient client) {
        this.client = client;
    }

    @Override
    public Client<Task> getClient() {
        return client;
    }
}
