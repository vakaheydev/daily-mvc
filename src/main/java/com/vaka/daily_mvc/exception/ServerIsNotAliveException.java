package com.vaka.daily_mvc.exception;

public class ServerIsNotAliveException extends RuntimeException {
    public ServerIsNotAliveException() {
        super("Server is not alive");
    }
}
