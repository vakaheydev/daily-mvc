package com.vaka.daily_mvc.service;

import com.vaka.daily_mvc.model.converter.ScheduleToDtoConverter;
import com.vaka.daily_mvc.model.dto.ScheduleDto;
import com.vaka.daily_client.client.Client;
import com.vaka.daily_client.client.blocked.ScheduleClient;
import com.vaka.daily_client.model.Schedule;
import org.springframework.stereotype.Service;

@Service
public class SimpleScheduleService extends AbstractService<Schedule> implements ScheduleService {
    ScheduleToDtoConverter scheduleToDtoConverter;
    ScheduleClient client;

    public SimpleScheduleService(ScheduleToDtoConverter scheduleToDtoConverter, ScheduleClient client) {
        this.scheduleToDtoConverter = scheduleToDtoConverter;
        this.client = client;
    }

    @Override
    public Client<Schedule> getClient() {
        return client;
    }

    @Override
    public ScheduleDto toDto(Schedule schedule) {
        return scheduleToDtoConverter.convert(schedule);
    }
}
