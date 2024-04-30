package com.lamnguyen.stationery_kimi.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    USER_NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "User not found"),
    TEST_ERROR(1001, "This is error message for test purpose");
    private int code;
    private String message;
}
