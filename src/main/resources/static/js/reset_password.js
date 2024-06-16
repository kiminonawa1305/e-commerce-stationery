$(document).ready(() => {
    $('#reset-password-form').validate({
        rules: {
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
            return true; // Prevent default form submission
        }
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