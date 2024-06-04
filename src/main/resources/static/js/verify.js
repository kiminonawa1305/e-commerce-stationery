$(document).ready(() => {
    $(".text_verify").keydown(function (el) {
        switch (el.keyCode) {
            case 8:
                $(this).val("")
                $(this).prev().focus();
                break;
            default:
                if ($(this).val().length === 1)
                    $(this).next().focus();
                break;
        }
    });

    $("#verify").click(e => {
        let key = "";
        $(".text_verify").each((i, el) => {
            key += $(el).val();
            if ($(el).val() === "") {
                $(el).focus();
                return false;
            }
        });

        if (key.length === 6) {
            Swal.fire({
                title: "Loading...",
                didOpen: () => {
                    Swal.showLoading();
                }
            });
            $.ajax({
                url: urlAPI + "api/auth/verify-email",
                type: "POST",
                data: {
                    id: `${sessionStorage.getItem("email")}`,
                    verificationCode: key
                },
                success: (data) => {
                    Swal.close()
                    Swal.fire({
                        icon: "success",
                        title: "Thành công!",
                        text: `${data.message}`,
                        confirmButtonText: "OK",
                        allowOutsideClick: false
                    }).then((result) => {
                        sessionStorage.removeItem("email")

                        window.location.href = url + "sign-in.html"
                    });
                },
                error: (xhr, status, err) => {
                    Swal.close()
                    Swal.fire({
                        icon: "error",
                        title: "Error",
                        text: `${xhr.responseJSON.message}`,
                        confirmButtonText: "OK",
                        allowOutsideClick: false
                    });
                }
            });
        }
    });
});