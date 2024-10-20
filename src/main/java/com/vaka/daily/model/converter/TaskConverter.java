package com.vaka.daily.model.converter;

import com.vaka.daily.model.dto.TaskDto;
import com.vaka.daily_client.model.Task;

public class TaskConverter {

    public static Task convertToTask(TaskDto taskDto) {
        Task task = new Task();

        task.setId(taskDto.getId());
        task.setName(taskDto.getName());
        task.setDescription(taskDto.getDescription());
        task.setDeadline(taskDto.getDeadline());
        task.setStatus(taskDto.getStatus());
        task.setSchedule(taskDto.getSchedule());

        return task;
    }

    public static TaskDto convertToTaskDto(Task task) {
        TaskDto taskDto = TaskDto.builder()
                .id(task.getId())
                .name(task.getName())
                .description(task.getDescription())
                .deadline(task.getDeadline())
                .status(task.getStatus())
                .schedule(task.getSchedule())
                .build();

        return taskDto;
    }

}
