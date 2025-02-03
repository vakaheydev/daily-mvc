package com.vaka.daily_mvc.model.form;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegistrationForm {
    @NotEmpty
    @Size(max = 100)
    private String login;

    @NotEmpty
    @Size(max = 100)
    private String password;

    private Long telegramId;
}
