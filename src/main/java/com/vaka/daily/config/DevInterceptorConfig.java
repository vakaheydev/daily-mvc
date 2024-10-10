package com.vaka.daily.config;

import com.vaka.daily.handlerinterceptor.LoggerInterceptor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Slf4j
@Profile("development")
@PropertySource("classpath:/application-dev.properties")
@Configuration
public class DevInterceptorConfig implements WebMvcConfigurer {
    @Value("${debugging.httplogs}")
    private boolean isHttpLoggingEnabled;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        if (isHttpLoggingEnabled) {
            registry.addInterceptor(new LoggerInterceptor());
        }
        log.info("Http logging: {}", isHttpLoggingEnabled);
    }
}
