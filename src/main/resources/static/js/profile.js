const format = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND'
})

$(document).ready(function () {
    const profileMenu = $('.profile-menu'),
        userFirstName = $('.user-first-name'),
        userLastName = $('.user-last-name'),
        userPhone = $('.user-phone'),
        oldPassword = $('input[name="oldPassword"]'),
        newPassword = $('input[name="newPassword"]'),
        confirmNewPassword = $('input[name="confirmNewPassword"]'),
        btnStatusBill = $(".btn-status-bill"),
        delivering = $("#delivering"),
        delivered = $("#delivered"),
        displayBills = $("#display-bills"),
        canceled = $("#canceled");


    profileMenu.click(function () {
        profileMenu.removeClass('bg-warning').addClass('bg-primary');
        $('.profile-menu').removeClass('active');
        $(this).removeClass("bg-primary").addClass('active').addClass("bg-warning");
        const index = $(this).data("index-slide");
        $(".slide").hide();
        $(`.slide[data-slide="${index}"]`).show();
        btnStatusBill.eq(0).click();
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

    delivering.on("click", function () {
        if ($(this).hasClass("active")) return
        updateListBill(displayBills, "delivering")
    });
    delivered.on("click", function () {
        if ($(this).hasClass("active")) return
        updateListBill(displayBills, "delivered")
    });

    canceled.on("click", function () {
        if ($(this).hasClass("active")) return
        updateListBill(displayBills, "canceled")
    });
    delivered.on("click", function () {
        if ($(this).hasClass("active")) return
        updateListBill(displayBills, "delivered")
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

const updateListBill = (displayBills, status) => {
    displayBills.empty()
    $.ajax({
        url: '/stationery_kimi/api/bill/' + status,
        type: 'GET',
        success: function (response) {
            const data = response.data;
            if (data) data.forEach(bill => {
                displayBills.append(loadBill(bill, status === "canceled"))
            });

            $(".btn-canceled-bill").on("click", function () {
                const billId = $(this).data("bill-id");
                Swal.fire({
                    title: "Bạn có chắc chắn muốn hủy đơn hàng này?",
                    icon: "warning",
                }).then((result) => {
                    if (result.isConfirmed)
                        cancelBill(billId);
                });
            });
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

const cancelBill = (billId) => {
    $.ajax({
        url: '/stationery_kimi/api/bill/cancel/' + billId,
        type: 'GET',
        success: function (response) {
            Toastify({
                text: "Hủy đơn hàng thành công",
                duration: 1000,
                close: true,
                gravity: "top",
                position: 'right',
                backgroundColor: "linear-gradient(to right, #00b09b, #96c93d)",
            }).showToast();

            $(`#bill-${billId}`).remove()
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

const loadBill = (bill, isCancel) => {
    let html = `<div id="bill-${bill.id}" class="row align-items-center mx-0 border border-1 border-secondary border-end-0 border-start-0">
                        <div class="col-2 border border-start-0 border-top-0 border-bottom-0 border-1 border-secondary text-primary"
                             style="padding-block: 20px">
                            #${bill.id}
                        </div>
                        <div class="col-2 border border-top-0 border-bottom-0 border-1 border-secondary"
                             style="padding-block: 8px">
                            <span>${bill.date}</span>
                        </div>
                        <div class="col-4 py-2 border border-top-0 border-bottom-0 border-1 border-secondary">
                            <p class="m-0">${bill.name}</p>
                            <p class="m-0">${bill.phone}</p>
                        </div>
                        <div class="col-2 border border-top-0 border-bottom-0 border-1 border-secondary text-center"
                             style="padding-block: 20px">
                            ${format.format(bill.totalPay)}
                        </div>
                        <div class="col-2 d-flex gap-2 justify-content-center border border-end-0 border-top-0 border-bottom-0 border-1 border-secondary"
                             style="padding-block: 12px">`
    if (!isCancel)
        html += `<button data-bill-id="${bill.id}" 
                            class="btn btn-see-bill-detail btn-primary d-inline-flex justify-content-center align-items-center"
                                    style="width: 40px; height: 40px">
                                <i class="fa-solid fa-eye"></i>
                            </button>
                            <button class="btn btn-danger btn-canceled-bill"
                            data-bill-id="${bill.id}"
                                    style="width: 40px; height: 40px">
                                <i class="fa-solid fa-xmark"></i>
                            </button>`
    html += `</div>
                   </div>`

    return html
}