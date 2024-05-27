import {url, urlAPI} from "./header.js";

$(document).ready(() => {
    let timerInterval;
    let loadHeader = false, loadForm = false, loadFooter = false;
    Swal.fire({
        title: "Loading...",
        didOpen: () => {
            Swal.showLoading();
            timerInterval = setInterval(() => {
                if (loadHeader && loadForm && loadFooter) {
                    Swal.close();
                    clearInterval(timerInterval);
                }
            }, 100);
        },
    }).then((result) => {
        $('#sign-up-form').validate({
            rules: {
                firstName: {
                    required: true,
                },
                lastName: {
                    required: true,
                },
                phone: {
                    required: true,
                    vietnamPhoneNumber: true
                },
                email: {
                    required: true,
                    email: true
                },
                password: {
                    required: true,
                    minlength: 8,
                    strongPasswordLowercase: true,
                    strongPasswordUppercase: true,
                    strongPasswordDigit: true,
                    strongPasswordSpecial: true
                },
                confirm_password: {
                    required: true,
                    equalTo: '#password'
                }
            },
            messages: {
                firstName: {
                    required: "Vui lòng nhập tên của bạn",
                },
                lastName: {
                    required: "Vui lòng nhập họ của bạn",
                },
                phone: {
                    required: "Vui lòng nhập số điện thoại của bạn",
                },
                email: {
                    required: "Vui lòng nhập email của bạn",
                    email: "Vui lòng nhập email hợp lệ"
                },
                password: {
                    required: "Vui lòng nhập mật khẩu",
                    minlength: "Vui lòng nhập ít nhất 8 ký tự"
                },
                confirm_password: {
                    required: "Vui lòng xác nhận mật khẩu",
                    equalTo: "Mật khẩu không khớp"
                }
            },
            submitHandler: function (form) {
                Swal.fire({
                    title: "Loading...",
                    didOpen: () => {
                        Swal.showLoading();
                    },
                });
                $.ajax({
                    url: urlAPI + 'api/auth/register',
                    type: 'POST',
                    dataType: 'json',
                    data: $(form).serialize(),
                    success: function (response) {
                        sessionStorage.setItem("email", response.data.email)
                        Swal.close();
                        Swal.fire({
                            title: "Đăng ký thành công!",
                            text: `${response.message}`,
                            icon: "success",
                            allowOutsideClick: false,
                            allowEscapeKey: false,
                        }).then((result) => {
                            window.location.href = url + "verify.html";
                        });
                    },
                    error: function (xhr, status, error) {
                        sessionStorage.setItem("email", $(form).email)
                        Swal.fire({
                            title: `${xhr.responseJSON.message}`,
                            icon: "error",
                            allowOutsideClick: false,
                            allowEscapeKey: false,
                        }).then((result) => {
                            window.location.href = url + "verify.html";
                        });
                    }
                });
                return false; // Prevent default form submission
            }
        });

    });
    $("header").load("../layout/header.html", () => {
        loadHeader = true;
    });
    $("#form").load("../layout/sign_up.html", () => {
        loadForm = true;
    });
    $("footer").load("../layout/footer.html", () => {
        loadFooter = true;
    });

    jQuery.validator.addMethod("strongPasswordLowercase", function (value, element) {
        // Password must be at least 8 characters long and contain at least one lowercase letter
        return this.optional(element) || /[a-z]/.test(value);
    }, "Mật khẩu phải chứa ít nhất 1 chữ thường");

    jQuery.validator.addMethod("strongPasswordUppercase", function (value, element) {
        // Password must be at least 8 characters long and contain at least one uppercase letter
        return this.optional(element) || /[A-Z]/.test(value);
    }, "Mật khẩu phải chứa ít nhất 1 chữ hoa");

    jQuery.validator.addMethod("strongPasswordDigit", function (value, element) {
        // Password must be at least 8 characters long and contain at least one digit
        return this.optional(element) || /[0-9]/.test(value);
    }, "Mật khẩu phải chứa ít nhất 1 số");

    jQuery.validator.addMethod("strongPasswordSpecial", function (value, element) {
        // Password must be at least 8 characters long and contain at least one special character
        return this.optional(element) || /[!@#$%^&*()_+\-=\[\]{};':"\\|,.<>\/?]+/.test(value);
    }, "Mật khẩu phải chứa ít nhất 1 ký tự đặc biệt");

    jQuery.validator.addMethod("vietnamPhoneNumber", function (value, element) {
        // Vietnamese phone number must start with "0" and have 10 or 11 digits
        return this.optional(element) || /^0[0-9]{9}$/.test(value);
    }, "Số điện thoại phải bắt đầu bằng số '0' và có 10 số");
});