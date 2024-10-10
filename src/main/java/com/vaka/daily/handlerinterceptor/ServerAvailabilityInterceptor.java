package com.vaka.daily.handlerinterceptor;

import com.vaka.daily.exception.ServerIsNotAliveException;
import com.vaka.daily.service.admin.UserService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
@Component
public class ServerAvailabilityInterceptor implements HandlerInterceptor {
    private final UserService userService;

    @Autowired
    public ServerAvailabilityInterceptor(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
            throws Exception {
        if (request.getRequestURI().contains("user") && !userService.isServerAlive()) {
            log.error("REST Server is not alive!");
            throw new ServerIsNotAliveException();
//            response.sendError(HttpServletResponse.SC_SERVICE_UNAVAILABLE, "Server is not available");
        }

        return true;
    }
}
