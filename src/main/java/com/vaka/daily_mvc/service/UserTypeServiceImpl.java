package com.vaka.daily_mvc.service;

import com.vaka.daily_client.client.Client;
import com.vaka.daily_client.client.blocked.UserTypeClient;
import com.vaka.daily_client.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserTypeServiceImpl extends AbstractService<UserType> implements UserTypeService {
    UserTypeClient userTypeClient;

    @Autowired
    public UserTypeServiceImpl(UserTypeClient userTypeClient) {
        this.userTypeClient = userTypeClient;
    }

    @Override
    public Client<UserType> getClient() {
        return userTypeClient;
    }
}
