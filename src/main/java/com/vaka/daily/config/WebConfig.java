package com.vaka.daily.config;

import com.vaka.daily.handlerinterceptor.ServerAvailabilityInterceptor;
import com.vaka.daily.model.converter.StringToScheduleListConverter;
import com.vaka.daily.model.converter.StringToUserTypeConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final ServerAvailabilityInterceptor interceptor;
    private final StringToUserTypeConverter stringToUserTypeConverter;
    private final StringToScheduleListConverter stringToScheduleListConverter;

    @Autowired
    public WebConfig(ServerAvailabilityInterceptor interceptor, StringToUserTypeConverter stringToUserTypeConverter,
                     StringToScheduleListConverter stringToScheduleListConverter) {
        this.interceptor = interceptor;
        this.stringToUserTypeConverter = stringToUserTypeConverter;
        this.stringToScheduleListConverter = stringToScheduleListConverter;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(interceptor);
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/home");
        registry.addViewController("/admin").setViewName("/admin/index");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToUserTypeConverter);
        registry.addConverter(stringToScheduleListConverter);
    }
}
