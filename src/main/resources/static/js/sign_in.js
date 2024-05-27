$(document).ready(() => {
    $('#sign-in-form').validate({
        rules: {
            email: {
                required: true,
                email: true
            },
            password: {
                required: true,
            }
        },
        messages: {
            email: {
                required: "Vui lòng nhập email của bạn",
                email: "Vui lòng nhập email hợp lệ"
            },
            password: {
                required: "Vui lòng nhập mật khẩu",
            },
        },
        submitHandler: function (form) {
            Swal.fire({
                title: "Loading...",
                didOpen: () => {
                    Swal.showLoading();
                },
            });
            $.ajax({
                url: urlAPI + 'api/auth/login',
                type: 'POST',
                dataType: 'json',
                data: $(form).serialize(),
                success: function (response) {
                    sessionStorage.setItem("user", JSON.stringify(response.data))
                    Swal.close();
                    Swal.fire({
                        title: "Đăng nhập thành công!",
                        text: `${response.message}`,
                        icon: "success",
                        allowOutsideClick: false,
                        allowEscapeKey: false,
                    }).then((result) => {
                        window.location.href = url + "index.html";
                    });
                },
                error: function (xhr, status, error) {
                    const responseJSON = xhr.responseJSON;
                    if (responseJSON) {
                        const code = responseJSON.code;
                        const message = responseJSON.message;
                        switch (code) {
                            case 406:
                                Swal.fire({
                                    title: `${message}`,
                                    icon: "error",
                                    allowOutsideClick: false,
                                    allowEscapeKey: false,
                                    timer: 5000
                                });
                                break
                            case 401:
                                Swal.fire({
                                    title: `${message}`,
                                    icon: "error",
                                    allowOutsideClick: false,
                                    allowEscapeKey: false,
                                }).then((result) => {
                                    window.location.href = url + "verify.html";
                                });
                                break
                        }
                    }
                }
            });
            return false; // Prevent default form submission
        }
    });
});