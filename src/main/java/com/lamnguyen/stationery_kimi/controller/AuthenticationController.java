package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.dto.UserDTO;
import com.lamnguyen.stationery_kimi.request.*;
import com.lamnguyen.stationery_kimi.response.ApiResponse;
import com.lamnguyen.stationery_kimi.service.IAuthenticationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    private IAuthenticationService authenticationService;


    @PostMapping("/register")
    public ApiResponse<UserDTO> register(@ModelAttribute @Valid UserRegisterRequest user) {
        UserDTO result = authenticationService.register(user);
        return ApiResponse.<UserDTO>builder()
                .message("Vui lòng kiểm tra email để xác thực tài khoản!")
                .data(result)
                .build();
    }

    @PostMapping("/verify-email")
    public ApiResponse<UserDTO> register(@ModelAttribute @Valid VerifyUserRequest verifyUserRequest) {
        UserDTO result = authenticationService.verify(verifyUserRequest);
        return ApiResponse.<UserDTO>builder()
                .message("Xác thực thành công!")
                .data(result)
                .build();
    }

    @PostMapping("/login")
    public ApiResponse<UserDTO> login(@Valid @ModelAttribute LoginRequest request, HttpSession httpSession) {
        String email = request.getEmail();
        String password = request.getPassword();
        UserDTO userDTO = authenticationService.login(email, password);
        httpSession.setAttribute("user", userDTO);
        return ApiResponse.<UserDTO>builder()
                .message("Đăng nhập thành công!")
                .data(userDTO)
                .build();
    }

    @PostMapping("/forget-password")
    public ApiResponse<Long> forgetPassword(@Valid @ModelAttribute ForgetPasswordRequest forgetPasswordRequest) {
        String email = forgetPasswordRequest.getEmail();
        return ApiResponse.<Long>builder()
                .message("Vui lòng kiểm tra email để đặt lại mật khẩu!")
                .code(202)
                .data(authenticationService.forgotPassword(email))
                .build();
    }

    @PostMapping("/reset-password")
    public ApiResponse<Void> changePassword(@Valid @ModelAttribute ResetPasswordRequest resetPasswordRequest) {
        String password = resetPasswordRequest.getPassword();
        Long id = resetPasswordRequest.getId();
        authenticationService.resetPassword(id, password);
        return ApiResponse.<Void>builder()
                .message("Đặt lại mật khẩu thành công!")
                .code(202)
                .build();
    }
}
