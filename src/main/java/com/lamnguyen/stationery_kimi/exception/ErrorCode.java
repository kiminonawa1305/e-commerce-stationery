package com.lamnguyen.stationery_kimi.exception;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ErrorCode {
    /*Test error code*/
    TEST_ERROR(1001, "This is error message for test purpose"),

    /*User error code*/
    USER_NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy người dùng."),

    /*Product error code*/
    PRODUCT_NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy sản phẩm."),
    NULL_ID_PRODUCT(HttpServletResponse.SC_NOT_FOUND, "Mã sản phẩm không được để trống."),
    PRODUCT_NOT_ENOUGH(HttpServletResponse.SC_UNAUTHORIZED, "Số lượng sản phẩm không đủ."),
    PRODUCT_OPTION_NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy tùy chọn sản phẩm."),
    PRODUCT_OPTION_NOT_ENOUGH(HttpServletResponse.SC_NOT_ACCEPTABLE, "Số lượng tùy chọn sản phẩm không đủ."),
    CART_ITEM_NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy sản phẩm trong giỏ hàng."),
    IMAGE_NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy hình ảnh."),
    INVALID_QUANTITY(HttpServletResponse.SC_NOT_ACCEPTABLE, "Số lượng không hợp lệ."),

    /*Provider error code*/
    NULL_ID_PROVIDER(HttpServletResponse.SC_NOT_FOUND, "Mã nhà cung cấp không được để trống."),
    PROVIDER_NOT_FOUND(HttpServletResponse.SC_NOT_FOUND, "Không tìm thấy nhà cung cấp."),

    /*User error code*/
    EMAIL_OR_PASSWORD_INCORRECT(HttpServletResponse.SC_NOT_ACCEPTABLE, "Email hoặc mật khẩu không chính xác."),
    NULL_ID_USER(HttpServletResponse.SC_NOT_FOUND, "Mã người dùng không được để trống."),
    EMAIL_EXISTED(HttpServletResponse.SC_NOT_ACCEPTABLE, "Email đã tồn tại."),
    USER_NOT_REGISTERED(HttpServletResponse.SC_NOT_ACCEPTABLE, "Người dùng chưa đăng ký."),
    WRONG_PASSWORD(HttpServletResponse.SC_NOT_ACCEPTABLE, "Mật khẩu không chính xác."),
    PASSWORD_NOT_MATCH(HttpServletResponse.SC_NOT_ACCEPTABLE, "Mật khẩu không trùng khớp."),
    WRONG_VERIFY_CODE(HttpServletResponse.SC_NOT_ACCEPTABLE, "Mã xác thực không chính xác."),
    VERIFY_CODE_EXPIRED(HttpServletResponse.SC_NOT_ACCEPTABLE, "Mã xác thực đã hết hạn. Vui lòng kiểm tra mail và nhập mã mới."),
    VERIFY_EMAIL(HttpServletResponse.SC_UNAUTHORIZED, "Vui lòng kiểm tra email để nhận mã xác thực!"),
    LOCK_ACCOUNT(HttpServletResponse.SC_NOT_ACCEPTABLE, "Tài khoản của bạn đã bị khóa."),
    ;

    private int code;
    private String message;
}
