package com.vaka.daily.service.admin;

import com.vaka.daily.service.AbstractService;
import com.vaka.daily_client.client.CommonClient;
import com.vaka.daily_client.client.blocked.UserTypeClient;
import com.vaka.daily_client.model.UserType;
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
    public CommonClient<UserType> getClient() {
        return userTypeClient;
    }
}
