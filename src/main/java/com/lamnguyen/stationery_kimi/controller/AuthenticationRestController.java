package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.dto.UserDTO;
import com.lamnguyen.stationery_kimi.request.LoginRequest;
import com.lamnguyen.stationery_kimi.request.UserRegisterRequest;
import com.lamnguyen.stationery_kimi.request.VerifyUserRequest;
import com.lamnguyen.stationery_kimi.dto.ApiResponse;
import com.lamnguyen.stationery_kimi.service.IAuthenticationService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationRestController {
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

    @PostMapping("/verify-email-forget-password")
    public ApiResponse<Void> verifyEmailForgetPassword(HttpSession session, @ModelAttribute @Valid VerifyUserRequest verifyUserRequest) {
        UserDTO result = authenticationService.verify(verifyUserRequest);
        session.setAttribute("id", result.getId());
        return ApiResponse.<Void>builder()
                .message("Xác thực thành công!")
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
}
