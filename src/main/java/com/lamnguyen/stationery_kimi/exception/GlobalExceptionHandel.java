package com.lamnguyen.stationery_kimi.exception;

import com.lamnguyen.stationery_kimi.response.ApiResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandel {
    @ExceptionHandler
    public ResponseEntity<ApiResponse<String>> handleException(ApplicationException e) {
        ApiResponse<String> body = ApiResponse.<String>builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(body);
    }
}
