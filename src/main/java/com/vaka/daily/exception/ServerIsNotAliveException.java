package com.vaka.daily.exception;

public class ServerIsNotAliveException extends RuntimeException {
    public ServerIsNotAliveException() {
        super("Server is not alive");
    }
}
