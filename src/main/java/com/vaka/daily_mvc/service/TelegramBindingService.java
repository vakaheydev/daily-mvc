package com.vaka.daily_mvc.service;

import com.vaka.daily_client.client.blocked.BindingTokenClient;
import jakarta.validation.ValidationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TelegramBindingService {
    private final BindingTokenClient bindingTokenClient;

    @Autowired
    public TelegramBindingService(BindingTokenClient bindingTokenClient) {
        this.bindingTokenClient = bindingTokenClient;
    }

    public String createToken(Integer userId) {
        return bindingTokenClient.createToken(userId).getValue();
    }

    public String getTokenByUserId(Integer userId) {
        return bindingTokenClient.getByUserId(userId).getValue();
    }
}
