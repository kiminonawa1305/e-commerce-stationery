$(document).ready(function () {
    const profileMenu = $('.profile-menu'),
        userFirstName = $('.user-first-name'),
        userLastName = $('.user-last-name'),
        userPhone = $('.user-phone'),
        oldPassword = $('input[name="oldPassword"]'),
        newPassword = $('input[name="newPassword"]'),
        confirmNewPassword = $('input[name="confirmNewPassword"]'),
        btnStatusBill = $(".btn-status-bill");


    profileMenu.click(function () {
        profileMenu.removeClass('bg-warning').addClass('bg-primary');
        $('.profile-menu').removeClass('active');
        $(this).removeClass("bg-primary").addClass('active').addClass("bg-warning");
        const index = $(this).data("index-slide");
        $(".slide").hide();
        $(`.slide[data-slide="${index}"]`).show();
    });

    profileMenu.eq(0).click()

    profileMenu.on("mouseenter", function () {
        profileMenu.not("button.active.profile-menu").addClass('bg-primary');
        profileMenu.not("button.active.profile-menu").removeClass('bg-warning');
        $(this).addClass('bg-warning');
    });

    profileMenu.on("mouseleave", function () {
        $(this).not("button.active.profile-menu").removeClass('bg-warning');
    });

    $("#update-profile").click(function () {
        updateProfile(userFirstName.val(), userLastName.val(), userPhone.val())
    });

    $("#update-password").click(function () {
        if (newPassword.val() !== confirmNewPassword.val()) {
            Toastify({
                text: "Mật khẩu mới không trùng khớp",
                duration: 1000,
                close: true,
                gravity: "top",
                position: 'right',
                backgroundColor: "linear-gradient(to right, #ff5f6d, #ffc371)",
            }).showToast();

            return;
        }

        $.ajax({
            url: '/stationery_kimi/api/user/update-password',
            type: 'PUT',
            contentType: 'application/json',
            data: JSON.stringify({
                oldPassword: oldPassword.val(),
                newPassword: newPassword.val(),
                confirmNewPassword: confirmNewPassword.val()
            }),
            success: function (response) {
                Toastify({
                    text: "Cập nhật mật khẩu thành công",
                    duration: 1000,
                    close: true,
                    gravity: "top",
                    position: 'right',
                    backgroundColor: "linear-gradient(to right, #00b09b, #96c93d)",
                }).showToast();

                oldPassword.val("")
                newPassword.val("")
                confirmNewPassword.val("")
            },
            error: function (data) {
                const message = data.responseJSON.message;
                Toastify({
                    text: message,
                    duration: 1000,
                    close: true,
                    gravity: "top",
                    position: 'right',
                    backgroundColor: "linear-gradient(to right, #ff5f6d, #ffc371)",
                }).showToast();
            }
        });
    });

    btnStatusBill.on("click", function () {
        btnStatusBill.removeClass("active").removeClass("bg-primary").removeClass("text-white");
        $(this).addClass("active").addClass("bg-primary").addClass("text-white");
    })

    btnStatusBill.on("mouseenter", function () {
        $(this).not(".btn-status-bill.active").addClass("bg-primary").addClass("text-white")
    })
    btnStatusBill.on("mouseleave", function () {
        $(this).not(".btn-status-bill.active").removeClass("bg-primary").removeClass("text-white")
    })
});

const updateProfile = (userFirstName, userLastName, userPhone) => {
    $.ajax({
        url: '/stationery_kimi/api/user/update-profile',
        type: 'PUT',
        contentType: 'application/json',
        data: JSON.stringify({
            firstName: userFirstName,
            lastName: userLastName,
            phone: userPhone
        }),
        success: function (response) {
            Toastify({
                text: "Cập nhật thông tin thành công",
                duration: 3000,
                close: true,
                gravity: "top",
                position: 'right',
                backgroundColor: "linear-gradient(to right, #00b09b, #96c93d)",
            }).showToast();

            const data = response.data;
            updateDataProfile(userFirstName, userLastName, userPhone, data)
        },
        error: function (data) {
            Toastify({
                text: "Cập nhật thông tin thất bại",
                duration: 3000,
                close: true,
                gravity: "top",
                position: 'right',
                backgroundColor: "linear-gradient(to right, #ff5f6d, #ffc371)",
            }).showToast();
        }
    });
}

const updateDataProfile = (userFirstName, userLastName, userPhone, data) => {
    $(".btn-user-first-name").text(data.firstName);
    $(".btn-user-last-name").text(data.lastName);
    $(".user-last-name-and-first-name").text(data.lastName + " " + data.firstName);
    userFirstName.text(data.firstName);
    userLastName.text(data.lastName);
    userPhone.text(data.phone);
}