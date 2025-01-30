package com.vaka.daily_mvc.config;

import com.vaka.daily_mvc.model.converter.StringToLocalDateTimeConverter;
import com.vaka.daily_mvc.model.converter.StringToScheduleListConverter;
import com.vaka.daily_mvc.model.converter.StringToUserTypeConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {
    private final StringToUserTypeConverter stringToUserTypeConverter;
    private final StringToScheduleListConverter stringToScheduleListConverter;
    private final StringToLocalDateTimeConverter stringToLocalDateTimeConverter;

    public WebConfig(StringToUserTypeConverter stringToUserTypeConverter,
                     StringToScheduleListConverter stringToScheduleListConverter,
                     StringToLocalDateTimeConverter stringToLocalDateTimeConverter) {
        this.stringToUserTypeConverter = stringToUserTypeConverter;
        this.stringToScheduleListConverter = stringToScheduleListConverter;
        this.stringToLocalDateTimeConverter = stringToLocalDateTimeConverter;
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addRedirectViewController("/", "/home");
        registry.addRedirectViewController("/user", "/user/start");
    }

    @Override
    public void addFormatters(FormatterRegistry registry) {
        registry.addConverter(stringToUserTypeConverter);
        registry.addConverter(stringToScheduleListConverter);
        registry.addConverter(stringToScheduleListConverter);
        registry.addConverter(stringToLocalDateTimeConverter);
    }
}
