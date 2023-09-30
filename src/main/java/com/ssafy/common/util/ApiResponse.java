package com.ssafy.common.util;

public class ApiResponse<T> {
    private T data;
    private String message;

    public ApiResponse() {
        // 기본 생성자
    }

    public ApiResponse(T data, String message) {
        this.data = data;
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
