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
    public ApiResponse<UserDTO> userDTOApiResponse(HttpSession session, @RequestBody User user) {
        UserDTO currentUser = (UserDTO) session.getAttribute("user");
        user.setId(currentUser.getId());
        user.setEmail(currentUser.getEmail());
        UserDTO userDTO = iUserService.save(user);
        session.setAttribute("user", user);
        return ApiResponse.<UserDTO>builder()
                .code(200)
                .message("Update profile successfully")
                .data(userDTO)
                .build();
    }
}
