package com.vaka.daily.service.admin;

import com.vaka.daily.service.AbstractService;
import com.vaka.dailyClient.client.Client;
import com.vaka.dailyClient.client.blocked.UserTypeClient;
import com.vaka.dailyClient.model.UserType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserTypeService extends AbstractService<UserType> implements UserTypeService {
    UserTypeClient userTypeClient;

    @Autowired
    public SimpleUserTypeService(UserTypeClient userTypeClient) {
        this.userTypeClient = userTypeClient;
    }

    @Override
    public Client<UserType> getClient() {
        return userTypeClient;
    }
}
