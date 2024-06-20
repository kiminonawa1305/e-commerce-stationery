package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.dto.ApiResponse;
import com.lamnguyen.stationery_kimi.dto.UserDTO;
import com.lamnguyen.stationery_kimi.entity.User;
import com.lamnguyen.stationery_kimi.service.IUserService;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserRestController {
    @Autowired
    private IUserService iUserService;

    @PutMapping("/update-profile")
    public ApiResponse<UserDTO> updateProfile(HttpSession session, @RequestBody User user) {
        UserDTO currentUser = (UserDTO) session.getAttribute("user");

        UserDTO userDTO = iUserService.changeProfile(currentUser.getId(), user);
        session.setAttribute("user", userDTO);
        return ApiResponse.<UserDTO>builder()
                .code(200)
                .message("Update profile successfully")
                .data(userDTO)
                .build();
    }

    @PutMapping("/update-password")
    public ApiResponse<UserDTO> updatePassword(HttpSession session, @RequestBody UpdatePasswordRequest user) {
        UserDTO currentUser = (UserDTO) session.getAttribute("user");
        UserDTO userDTO = iUserService.updatePassword(currentUser.getId(), user);
        session.setAttribute("user", userDTO);
        return ApiResponse.<UserDTO>builder()
                .code(200)
                .message("Update profile successfully")
                .data(userDTO)
                .build();
    }

    public record UpdatePasswordRequest(
            String oldPassword,
            String newPassword,
            String confirmNewPassword) {
    }
}
