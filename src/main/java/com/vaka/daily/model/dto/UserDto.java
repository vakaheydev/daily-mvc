package com.vaka.daily.model.dto;

import com.vaka.daily_client.model.UserType;

public class UserDto {
    private Integer id;
    private String login;
    private UserType userType;

    public UserDto() {
    }

    public UserDto(Integer id, String login, UserType userType) {
        this.id = id;
        this.login = login;
        this.userType = userType;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setUserType(UserType userType) {
        this.userType = userType;
    }
}
