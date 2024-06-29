package com.vaka.daily.config;

import com.vaka.daily.requesthandler.ServerAvailabilityInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class HandlerInterceptorConfig implements WebMvcConfigurer {
    private final ServerAvailabilityInterceptor interceptor;

    @Autowired
    public HandlerInterceptorConfig(ServerAvailabilityInterceptor interceptor) {
        this.interceptor = interceptor;
    }

    @Override

    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }
}
