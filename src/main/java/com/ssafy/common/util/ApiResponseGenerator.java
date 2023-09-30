package com.ssafy.common.util;

public class ApiResponseGenerator {
    public static <T> ApiResponse<T> of(T data) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setData(data);
        response.setMessage("Success");
        return response;
    }

    public static <T> ApiResponse<T> of(T data, String message) {
        ApiResponse<T> response = new ApiResponse<>();
        response.setData(data);
        response.setMessage(message);
        return response;
    }


}

