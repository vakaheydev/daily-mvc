package com.vaka.daily_mvc.model.converter;

import com.vaka.daily_client.model.TaskType;
import com.vaka.daily_mvc.service.TaskTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToTaskTypeConverter implements Converter<String, TaskType> {
    TaskTypeService taskTypeService;

    @Autowired
    public StringToTaskTypeConverter(TaskTypeService taskTypeService) {
        this.taskTypeService = taskTypeService;
    }

    @Override
    public TaskType convert(String source) {
        return taskTypeService.getById(Integer.parseInt(source));
    }
}
