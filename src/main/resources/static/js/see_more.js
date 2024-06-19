function getData() {
    return {
        productId: $("input[name='id']").val(),
        quantity: $("input[name='quantity']").val(),
        productOptionId: $("input[name='product-option']:checked").val()
    }
}

$(document).ready(function () {
    $('.carousel-item.product-image').eq(0).addClass('active');

    const productOption = $(".product-option");
    productOption.click(function () {
        productOption.removeClass('border-primary');
        productOption.removeClass('border-secondary');
        productOption.addClass('border-secondary');
        $(this).removeClass('border-secondary');
        $(this).addClass('border-primary');

        $("#quantity").val(1)

        const maxQuantity = $(this).attr("data-quantity")
        if (parseInt(maxQuantity) < 1) $("#button-see-more").hide()
        else $("#button-see-more").show()
    });

    productOption.eq(0).click()

    $("#button-hidden-see-more").on("click", (e) => {
        $("#see-more").html("");
    })

    $("#decrease").click(function () {
        let quantity = $(this).next().val();
        if (quantity > 1) {
            $("input[name='quantity']").val(--quantity);
        }
    })
    $("#increase").click(function () {
            const maxQuantity = $("input[name='product-option']:checked").attr("data-quantity")
            let quantity = $(this).prev().val();
            if (parseInt(maxQuantity) > quantity)
                $("input[name='quantity']").val(++quantity);
        }
    )

    const buttonAddCart = $(".button-add-to-cart");
    buttonAddCart.on("mouseenter", function () {
        $(this).addClass("bg-primary")
        $(this).removeClass("bg-white")
        $(this).addClass("text-white")
        $(this).removeClass("text-primary")
    });

    buttonAddCart.on("mouseleave", function () {
        $(this).removeClass("bg-primary")
        $(this).addClass("bg-white")
        $(this).addClass("text-primary")
        $(this).removeClass("text-white")
    });


    buttonAddCart.click(() => {
        $.ajax({
            data: JSON.stringify(getData()),
            url: "api/cart/add",
            method: "POST",
            contentType: "application/json",
            success: (response) => {
                console.log(response.data)
                Toastify({
                    text: "Thêm vào giỏ hàng thành công!",
                    duration: 1000,
                    backgroundColor: "#4cea06"
                }).showToast();

                $(".cart-menu-amount").text(response.data)
                $("#quantity").val(1)
            },
            error: (error) => {
                console.log(error)
                Toastify({
                    text: "Lỗi thêm vào giỏ hàng!",
                    duration: 1000,
                    backgroundColor: "#ea0606"
                }).showToast();
            }
        })
    })
});