package com.ssafy.common.exception.handler;

public class FileServiceException extends RuntimeException {
    public FileServiceException(String message) {
        super(message);
    }
    public FileServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}

