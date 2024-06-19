package com.lamnguyen.stationery_kimi.exception;

import com.lamnguyen.stationery_kimi.dto.ApiResponse;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandel {
    @ExceptionHandler(ApplicationException.class)
    public ResponseEntity<ApiResponse<String>> handleException(ApplicationException e) {
        ApiResponse<String> body = ApiResponse.<String>builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiResponse<String>> handleRuntimeException(RuntimeException e) {
        ApiResponse<String> body = ApiResponse.<String>builder()
                .code(HttpServletResponse.SC_BAD_REQUEST)
                .message("Lỗi hệ thống")
                .build();
        return ResponseEntity.badRequest().body(body);
    }

//    @ExceptionHandler(NoResourceFoundException.class)
//    public ResponseEntity<ApiResponse<String>> handleNoResourceFoundException(NoResourceFoundException e) {
//        ApiResponse<String> body = ApiResponse.<String>builder()
//                .code(HttpServletResponse.SC_NOT_FOUND)
//                .message("Không tìm thấy tài nguyên")
//                .build();
//        return ResponseEntity.badRequest().body(body);
//    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiResponse<String>> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException e) {
        ApiResponse<String> body = ApiResponse.<String>builder()
                .code(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE)
                .message("Lỗi kiểu dữ liệu")
                .build();
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiResponse<Map<String, String>>> handleMethodArgumentNotValidException(MethodArgumentNotValidException e) {
        System.out.println(e.getMessage());
        Map<String, String> mapError = new HashMap<>();
        e.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            mapError.put(fieldName, errorMessage);
        });
        ApiResponse<Map<String, String>> body = ApiResponse.<Map<String, String>>builder()
                .code(HttpServletResponse.SC_UNSUPPORTED_MEDIA_TYPE)
                .message("Dữ liệu không hợp lệ")
                .data(mapError)
                .build();
        return ResponseEntity.badRequest().body(body);
    }

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<ApiResponse<String>> handleHttpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        ApiResponse<String> body = ApiResponse.<String>builder()
                .code(HttpServletResponse.SC_METHOD_NOT_ALLOWED)
                .message("Phương thức không được hỗ trợ")
                .build();
        return ResponseEntity.badRequest().body(body);
    }
}
