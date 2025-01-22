package com.vaka.daily_mvc.model.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.vaka.daily_client.model.Schedule;
import com.vaka.daily_client.model.UserNotification;
import com.vaka.daily_client.model.UserType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
    private Integer id;

    @NotEmpty
    private String login;
    @NotEmpty
    @Size(max = 100)
    private String password;
    @NotEmpty
    @Size(max = 100)
    private String firstName;
    @NotEmpty
    @Size(max = 100)
    private String secondName;
    @Size(max = 100)
    private String patronymic;

    @NotNull
    private UserType userType;

    @JsonIgnoreProperties("userDto")
    private List<ScheduleDto> schedules = new ArrayList<>();

    private Long telegramId;

    private UserNotification userNotification;
}
