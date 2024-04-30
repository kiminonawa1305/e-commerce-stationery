package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.exception.ApplicationException;
import com.lamnguyen.stationery_kimi.exception.ErrorCode;
import com.lamnguyen.stationery_kimi.response.ApiResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/hello-world")
    public ApiResponse<String> helloWorld() {
        return ApiResponse.<String>builder()
                .message("Hello World")
                .build();
    }

    @GetMapping("error")
    public ApiResponse<String> error() {
        throw new ApplicationException(ErrorCode.TEST_ERROR);
    }
}
