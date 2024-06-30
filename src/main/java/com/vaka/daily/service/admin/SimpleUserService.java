package com.vaka.daily.service.admin;

import com.vaka.daily.service.AbstractService;
import com.vaka.daily_client.client.CommonClient;
import com.vaka.daily_client.client.blocked.UserClient;
import com.vaka.daily_client.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SimpleUserService extends AbstractService<User> implements UserService {
    UserClient userClient;

    @Autowired
    public SimpleUserService(UserClient userClient) {
        this.userClient = userClient;
    }

    @Override
    public CommonClient<User> getClient() {
        return userClient;
    }
}
