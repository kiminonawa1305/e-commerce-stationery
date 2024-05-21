package com.lamnguyen.stationery_kimi.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class LoginRequest {
    @Email(message = "Email không hợp lệ!")
    @NotNull(message = "Email không được để trống!")
    @NotBlank(message = "Email không được để trống!")
    private String email;
    @NotNull(message = "Mật khẩu không được để trống!")
    @NotBlank(message = "Mật khẩu không được để trống!")
    private String password;
}
