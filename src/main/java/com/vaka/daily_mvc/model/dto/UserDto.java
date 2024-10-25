package com.vaka.daily_mvc.model.dto;

import com.vaka.daily_client.model.UserType;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserDto {
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
}
