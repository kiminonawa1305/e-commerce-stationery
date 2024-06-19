const format = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND'
})

$(document).ready(function () {
    const province = $("#select-province"),
        district = $("#select-district"),
        commune = $("#select-commune"),
        typePayment = $("input[name='payment']"),
        displayQr = $("#display-qr"),
        forms = $('.needs-validation'),
        totalPrice = $(".total-price-cart"),
        totalDiscount = $(".total-discount-cart"),
        totalPay = $(".total-pay-cart"),
        totalAmount = $(".cart-menu-amount");

    /*Load danh sach tinh*/
    loadProvince();

    // Loop over them and prevent submission
    forms.on('submit', function (event) {
        if (!this.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
        }
        $(this).addClass('was-validated');
    });

    /*Hien thi o nhap thong tin xuat hoa don*/
    $('#checkbox-bill').change(function () {
        if ($(this).is(':checked')) {
            $('#bill-field').removeClass('d-none');
            $('#bill-field input').attr('required', true);
        } else {
            $('#bill-field').addClass('d-none');
            $('#bill-field input').attr('required', false);
        }
    });

    /*Chuc nanwg tang so luong*/
    $(".increase").on("click", function () {
        const input = $(this).prev();
        const cartItemId = input.attr('data-cart-item-id')
        $.ajax({
            url: "./api/cart/increase",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "cartItemId": cartItemId,
                "quantity": 1
            }),
            success: (response) => {
                input.val(parseInt(input.val()) + 1)
                const totalPayItem = $(`.cart-item-price[data-cart-item-id=${cartItemId}]`)
                updateCart(response.data, totalPrice, totalDiscount, totalPay, totalAmount, totalPayItem)
                Toastify({
                    text: "Thành công!",
                    duration: 1000,
                    "backgroundColor": "#4cea06"
                }).showToast();
            },
            error: (err) => {
                console.log(err)
                Toastify({
                    text: err.response.message,
                    duration: 1000,
                    backgroundColor: "#ea0606"
                }).showToast();
            }
        });
    });

    /*Chuc nang gian so luong*/
    $(".decrease").on("click", function () {
        const input = $(this).next();
        const cartItemId = input.attr('data-cart-item-id')
        if (parseInt(input.val()) <= 1) return
        $.ajax({
            url: "./api/cart/decrease",
            method: "POST",
            contentType: "application/json",
            data: JSON.stringify({
                "cartItemId": cartItemId,
                "quantity": 1
            }),
            success: (response) => {
                input.val(parseInt(input.val()) - 1)
                const totalPayItem = $(`.cart-item-price[data-cart-item-id=${cartItemId}]`)
                updateCart(response.data, totalPrice, totalDiscount, totalPay, totalAmount, totalPayItem)
                Toastify({
                    text: "Thành công!",
                    duration: 1000,
                    "backgroundColor": "#4cea06"
                }).showToast();
            },
            error: (err) => {
                console.log(err)
                Toastify({
                    text: err.response.message,
                    duration: 1000,
                    backgroundColor: "#ea0606"
                }).showToast();
            }
        });
    });

    /*Xoa san ham cart item*/
    $(".delete-cart-item").on("click", function () {
        const cartItemId = $(this).attr('data-cart-item-id')
        $.ajax({
            url: "./api/cart/delete",
            method: "DELETE",
            contentType: "application/json",
            data: JSON.stringify({
                "cartItemId": cartItemId,
            }),
            success: (response) => {
                updateCart(response.data, totalPrice, totalDiscount, totalPay, totalAmount)
                Toastify({
                    text: "Thành công!",
                    duration: 1000,
                    "backgroundColor": "#4cea06"
                }).showToast();

                $(`.cart-item[data-cart-item-id=${cartItemId}]`).remove()
                totalAmount.text(parseInt(totalAmount.text()) - 1)
            },
            error: (err) => {
                console.log(err)
                Toastify({
                    text: err.response.message,
                    duration: 1000,
                    backgroundColor: "#ea0606"
                }).showToast();
            }
        });
    })

    /*Chuc nang chon tinh giao hang*/
    province.on("change", function () {
        resetDistrict(district);
        resetCommune(commune);

        $.ajax({
            url: `https://esgoo.net/api-tinhthanh/2/${province.val()}.htm`,
            type: "GET",
            success: function (response) {
                response.data.forEach(district => {
                    $("#select-district").append(`<option value="${district.id}">${district["full_name"]}</option>`);
                });
            }
        });
    })

    /*Chuc nang hien thi huyen giao hang*/
    district.on("change", function () {
        resetCommune(commune);

        $.ajax({
            url: `https://esgoo.net/api-tinhthanh/3/${district.val()}.htm`,
            type: "GET",
            success: function (response) {
                response.data.forEach(commune => {
                    $("#select-commune").append(`<option value="${commune.id}">${commune["full_name"]}</option>`);
                });
            }
        });
    })

    /*Hien thi  xa giao hang*/
    commune.on("change", function () {
        $.ajax({
            url: `https://esgoo.net/api-tinhthanh/5/${commune.val()}.htm`,
            type: "GET",
            success: function (response) {
                $("#input-full-address").val(response.data["full_name"]);
            }
        });
    })

    /*Chon hinh thuc thanh toan*/
    typePayment.on("change", function () {
        typePayment.parent().removeClass("border-primary").removeClass("border-2");
        typePayment.not(this).parent().addClass("border-secondary").addClass("border-1");
        $(this).parent().removeClass("border-secondary").removeClass("border-1");
        $(this).parent().addClass("border-primary").addClass("border-2");
    });

    /*Hien thi qr thanh toan*/
    $("#qr-pay").on("change", function () {
        const total = $(".total-price-cart").text().replace(/[^0-9]/g, "");
        loadQrPay(displayQr, total);
    });

    /*An qr thanh toan neu chon hinh thuc khac*/
    typePayment.not("#qr-pay").on("change", function () {
        displayQr.empty();
    });

    /*thanh toan gio hang*/
    $('#form').on('submit', function (event) {
        event.preventDefault(); // Ngăn chặn hành động submit mặc định
        const userId = $("#user-id")

        if (!userId || !userId.data("user-id")) {
            Swal.fire({
                icon: 'Lỗi',
                title: 'Oops...',
                text: 'Vui lòng đăng nhập để tiếp tục!',
            }).then((result) => {
                if (result.isConfirmed)
                    window.location.href = "./sign-in.html"
            });

            return;
        }

        if (!$("input[name=cartItemId]").length) {
            Swal.fire({
                icon: 'Lỗi',
                title: 'Oops...',
                text: 'Không có sản phẩm nào trong giỏ hàng!',
            })

            return;
        }

        if (!province.val()) {
            Swal.fire({
                icon: 'Lỗi',
                title: 'Oops...',
                text: 'Vui lòng chọn tỉnh/thành phố giao hàng!',
            })

            return;
        }

        if (!district.val()) {
            Swal.fire({
                icon: 'Lỗi',
                title: 'Oops...',
                text: 'Vui lòng chọn quận/huyện giao hàng!',
            })

            return;
        }

        if (!commune.val()) {
            Swal.fire({
                icon: 'Lỗi',
                title: 'Oops...',
                text: 'Vui lòng chọn xã/phường giao hàng!',
            })

            return;
        }

        this.submit();
    });
});

const updateCart = (data, totalPrice, totalDiscount, totalPay, totalAmount, totalPayItem) => {
    totalPrice.text(format.format(data.totalPrice))
    totalDiscount.text(format.format(data.totalDiscount))
    totalPay.text(format.format(data.totalPay))
    totalAmount.text(format.format(data.totalAmount))
    totalPayItem.text(format.format(data.totalPayItem))
}


const loadProvince = () => {
    $.ajax({
        url: "https://esgoo.net/api-tinhthanh/1/0.htm",
        type: "GET",
        success: function (response) {
            response.data.forEach(province => {
                $("#select-province").append(`<option value="${province.id}">${province["full_name"]}</option>`);
            });
        }
    });
}

const resetDistrict = (district) => {
    district.empty();
    district.append(`<option disabled selected>Chọn quận/huyện</option>`);
}

const resetCommune = (commune) => {
    commune.empty();
    commune.append(`<option disabled selected>Chọn xã/phường</option>`);
}

const loadQrPay = (displayQr, total) => {
    $.ajax({
        url: "https://api.vietqr.io/v2/generate",
        method: "POST",
        header: {
            "x-client-id": "a6f87be3-a595-433e-ac40-5cb6ef40b95f",
            "x-api-key": "ba5cb3e6-6e90-4363-bfe6-7b479779ac45",
            "Content-Type": "application/json",
        },
        data: {
            "accountNo": "0855354919",
            "accountName": "Văn phòng phẩm Kimi",
            "acqId": "963388",
            "addInfo": "Thanh toán đơn hàng Văn phòng phẩm Kimi",
            "amount": total,
            "template": "compact"
        },
        success: function (response) {
            displayQr.empty().append(`<img class="w-100" src="${response.data.qrDataURL}" alt="qr-pay.png"/>`)
        }
    });
}