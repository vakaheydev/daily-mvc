package com.vaka.daily.model.converter;

import com.vaka.dailyClient.model.UserType;
import com.vaka.dailyClient.model.UserTypes;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class StringToUserTypeConverter implements Converter<String, UserType> {
    @Override
    public UserType convert(String source) {
        return UserTypes.valueOf(source.toUpperCase()).getType();
    }
}
