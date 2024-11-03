package com.vaka.daily_mvc.model.converter;

import com.vaka.daily_client.model.UserType;
import com.vaka.daily_client.model.UserTypes;
import com.vaka.daily_mvc.service.UserTypeService;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToUserTypeConverter implements Converter<String, UserType> {
    UserTypeService userTypeService;

    public StringToUserTypeConverter(UserTypeService userTypeService) {
        this.userTypeService = userTypeService;
    }

    @Override
    public UserType convert(String source) {
        return userTypeService.getByUniqueName(source.toLowerCase());
    }
}
