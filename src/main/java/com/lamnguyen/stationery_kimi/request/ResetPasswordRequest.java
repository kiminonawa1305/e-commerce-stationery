package com.lamnguyen.stationery_kimi.request;

import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ResetPasswordRequest {
    @NotNull(message = "ID không được để trống!")
    private Long id;

    @Pattern(regexp = "^(?=.*[a-z]).+$", message = "Mật khẩu phải chứa ít nhất 1 chữ cái thường!")
    @Pattern(regexp = "^(?=.*[A-Z]).+$", message = "Mật khẩu phải chứa ít nhất 1 chữ cái in hoa!")
    @Pattern(regexp = "^(?=.*[0-9]).+$", message = "Mật khẩu phải chứa ít nhất 1 chữ số!")
    @Pattern(regexp = "^(?=.*[@#$%^&+=]).+$", message = "Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt!")
    @Pattern(regexp = "^.{8,}$", message = "Mật khẩu phải chứa ít nhất 8 ký tự!")
    @NotNull(message = "Mật khẩu không được để trống!")
    @NotBlank(message = "Mật khẩu không được để trống!")
    private String password;

    @Pattern(regexp = "^(?=.*[a-z]).+$", message = "Mật khẩu phải chứa ít nhất 1 chữ cái thường!")
    @Pattern(regexp = "^(?=.*[A-Z]).+$", message = "Mật khẩu phải chứa ít nhất 1 chữ cái in hoa!")
    @Pattern(regexp = "^(?=.*[0-9]).+$", message = "Mật khẩu phải chứa ít nhất 1 chữ số!")
    @Pattern(regexp = "^(?=.*[@#$%^&+=]).+$", message = "Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt!")
    @Pattern(regexp = "^.{8,}$", message = "Mật khẩu phải chứa ít nhất 8 ký tự!")
    @NotNull(message = "Mật khẩu xác thật không được để trống!")
    @NotBlank(message = "Mật khẩu xác thật không được để trống!")
    private String confirmPassword;

    @AssertTrue(message = "Mật khẩu không khớp!")
    private boolean confirmPassword() {
        if (confirmPassword == null) return true;
        return password.equals(confirmPassword);
    }
}
