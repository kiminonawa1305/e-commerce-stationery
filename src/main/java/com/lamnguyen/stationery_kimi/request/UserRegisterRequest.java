package com.lamnguyen.stationery_kimi.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserRegisterRequest {
    @NotNull(message = "Họ không được để trống!")
    @NotBlank(message = "Họ không được để trống!")
    private String firstName;
    @Column
    @NotNull(message = "Tên không được để trống!")
    @NotBlank(message = "Tên không được để trống!")
    private String lastName;

    @Column
    @NotNull(message = "Số điện thoại không được để trống!")
    @NotBlank(message = "Số điện thoại không được để trống!")
    private String phone;

    @Column
    @Email(message = "Email không hợp lệ!")
    @NotNull(message = "Email không được để trống!")
    @NotBlank(message = "Email không được để trống!")
    private String email;

    @Column
    @Pattern(regexp = "^(?=.*[a-z]).+$", message = "Mật khẩu phải chứa ít nhất 1 chữ cái thường!")
    @Pattern(regexp = "^(?=.*[A-Z]).+$", message = "Mật khẩu phải chứa ít nhất 1 chữ cái in hoa!")
    @Pattern(regexp = "^(?=.*[0-9]).+$", message = "Mật khẩu phải chứa ít nhất 1 chữ số!")
    @Pattern(regexp = "^(?=.*[@#$%^&+=]).+$", message = "Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt!")
    @Pattern(regexp = "^.{8,}$", message = "Mật khẩu phải chứa ít nhất 8 ký tự!")
    @NotNull(message = "Password không được để trống!")
    @NotBlank(message = "Password không được để trống!")
    private String password;

    @Column
    @ColumnDefault("'user'")
    private String role;
}
