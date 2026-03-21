package com.wetech.backend_spring_wetech.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Generic API response wrapper used by controllers.
 * @param <T> payload type
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ApiResponse<T> {
    /**
     * Application-specific status code (e.g. 0 = success, non-zero = error)
     */
    private int code;

    /**
     * Human-readable message for the response
     */
    private String message;

    /**
     * Actual result payload
     */
    private T result;

    public static <T> ApiResponse<T> success(T result) {
        return ApiResponse.<T>builder()
                .code(0)
                .message("success")
                .result(result)
                .build();
    }

    public static <T> ApiResponse<T> of(int code, String message, T result) {
        return ApiResponse.<T>builder()
                .code(code)
                .message(message)
                .result(result)
                .build();
    }

    public static <T> ApiResponse<T> error(String message) {
        return ApiResponse.<T>builder()
                .code(-1)
                .message(message)
                .result(null)
                .build();
    }
}

