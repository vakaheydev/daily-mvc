package com.vaka.daily_mvc.handlerinterceptor;

import com.vaka.daily_client.model.UserTypes;
import com.vaka.daily_mvc.service.authorization.AuthorizationService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import java.io.IOException;
import java.util.List;

@Slf4j
@Component
public class AuthorizationHandlerInterceptor implements HandlerInterceptor {
    private final AuthorizationService service;

    public AuthorizationHandlerInterceptor(AuthorizationService service) {
        this.service = service;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        if (isAuthorizationNeeded(request)) {
            Cookie[] cookies = request.getCookies();
            return handleCookies(cookies, request, response);
        }

        return true;
    }

    private boolean handleCookies(Cookie[] cookies, HttpServletRequest request, HttpServletResponse response) {
        Cookie usernameCookie = null;

        if (cookies == null) {
            return false;
        }

        for (var cookie : cookies) {
            if (cookie.getName().equals("username")) {
                usernameCookie = cookie;
            }
        }

        if (usernameCookie == null) {
            sendRedirectToLogin(request, response);
            return false;
        }

        if (!isValidUsernameCookie(usernameCookie)) {
            sendRedirectToLogin(request, response);
            return false;
        }

        if (!hasAccess(usernameCookie, request)) {
            sendRedirectToHome(response);
            return false;
        }

        return true;
    }

    private boolean hasAccess(Cookie usernameCookie, HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        if (requestURI.contains("admin")) {
            return service.hasRole(usernameCookie.getValue(), UserTypes.ADMIN);
        }

        return true;
    }

    private void sendRedirectToLogin(HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect("/authorization/login?from=" + request.getRequestURI());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void sendRedirectToHome(HttpServletResponse response) {
        try {
            response.sendRedirect("/home");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private boolean isValidUsernameCookie(Cookie usernameCookie) {
        return service.checkUsername(usernameCookie.getValue());
    }

    private boolean isAuthorizationNeeded(HttpServletRequest request) {
        String requestURI = request.getRequestURI();

        List<String> safeLocations = List.of(
                "/",
                "/home",
                "/authorization",
                "/favicon"
        );

        return !isSafeLocation(requestURI, safeLocations);
    }

    private boolean isSafeLocation(String uri, List<String> safeLocations) {
        for (String location : safeLocations) {
            if (location.equals(uri)) {
                return true;
            }

            if (!location.equals("/") && uri.contains(location)) {
                return true;
            }
        }

        return false;
    }
}
