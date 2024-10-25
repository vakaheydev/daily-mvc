package com.vaka.daily_mvc.config;

import com.vaka.daily_mvc.handlerinterceptor.ServerAvailabilityInterceptor;
import com.vaka.daily_mvc.model.converter.StringToLocalDateTimeConverter;
import com.vaka.daily_mvc.model.converter.StringToScheduleListConverter;
import com.vaka.daily_mvc.model.converter.StringToUserTypeConverter;
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
    private final StringToLocalDateTimeConverter stringToLocalDateTimeConverter;

    public WebConfig(ServerAvailabilityInterceptor interceptor, StringToUserTypeConverter stringToUserTypeConverter,
                     StringToScheduleListConverter stringToScheduleListConverter,
                     StringToLocalDateTimeConverter stringToLocalDateTimeConverter) {
        this.interceptor = interceptor;
        this.stringToUserTypeConverter = stringToUserTypeConverter;
        this.stringToScheduleListConverter = stringToScheduleListConverter;
        this.stringToLocalDateTimeConverter = stringToLocalDateTimeConverter;
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
        registry.addConverter(stringToScheduleListConverter);
        registry.addConverter(stringToLocalDateTimeConverter);
    }
}
