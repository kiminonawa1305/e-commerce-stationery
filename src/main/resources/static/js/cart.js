const format = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND'
})

$(document).ready(function () {
    let forms = $('.needs-validation');
    // Loop over them and prevent submission
    forms.on('submit', function (event) {
        if (!this.checkValidity()) {
            event.preventDefault();
            event.stopPropagation();
        }
        $(this).addClass('was-validated');
    });

    $('#checkbox-bill').change(function () {
        if ($(this).is(':checked')) {
            $('#bill-field').removeClass('d-none');
            $('#bill-field input').attr('required', true);
        } else {
            $('#bill-field').addClass('d-none');
            $('#bill-field input').attr('required', false);
        }
    });

    const totalPriceCart = $(".cart-price-total");


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
                const totalPrice = $(`.cart-item-price[data-cart-item-id=${cartItemId}]`)
                updateCart(response.data, totalPrice, totalPriceCart)
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
                const totalPrice = $(`.cart-item-price[data-cart-item-id=${cartItemId}]`)
                updateCart(response.data, totalPrice, totalPriceCart)
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
                totalPriceCart.text(format.format(response.data))
                Toastify({
                    text: "Thành công!",
                    duration: 1000,
                    "backgroundColor": "#4cea06"
                }).showToast();

                $(`.cart-item[data-cart-item-id=${cartItemId}]`).remove()
                const amount = $(".cart-menu-amount");
                amount.text(parseInt(amount.text()) - 1)
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
});

const updateCart = (data, totalPrice, totalPriceCart) => {
    totalPrice.text(format.format(data.totalPriceCartItem))
    totalPriceCart.text(format.format(data.totalPriceCart))
}