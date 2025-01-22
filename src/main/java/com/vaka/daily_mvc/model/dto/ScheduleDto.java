package com.vaka.daily_mvc.model.dto;

import com.vaka.daily_client.model.Task;

import java.util.List;

public class ScheduleDto {
    private Integer id;
    private String name;
    private UserDto user;
    private List<Task> tasks;

    public ScheduleDto() {
    }

    public ScheduleDto(Integer id, String name, UserDto userDto, List<Task> tasks) {
        this.id = id;
        this.name = name;
        this.user = userDto;
        this.tasks = tasks;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
