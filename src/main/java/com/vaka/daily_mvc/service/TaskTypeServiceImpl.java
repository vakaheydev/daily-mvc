package com.vaka.daily_mvc.service;

import com.vaka.daily_client.client.Client;
import com.vaka.daily_client.client.blocked.TaskTypeClient;
import com.vaka.daily_client.model.TaskType;
import org.springframework.stereotype.Service;

@Service
public class TaskTypeServiceImpl extends AbstractService<TaskType> implements TaskTypeService {
    TaskTypeClient client;

    public TaskTypeServiceImpl(TaskTypeClient client) {
        this.client = client;
    }

    @Override
    public Client<TaskType> getClient() {
        return client;
    }
}
