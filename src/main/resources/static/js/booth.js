const formatCurrency = new Intl.NumberFormat('vi-VN', {"style": "currency", "currency": "VND"})
$(document).ready(function () {
    const filterByBrand = $('.filter-by-brand');
    const filterByPrice = $('.filter-by-price');
    const sortName = $('input[name="sort-name"]');
    const sortPrice = $('input[name="sort-price"]');
    const sortNewProduct = $('input[name="sort-new-product"]');
    const categoryId = $("main").data("category-id");

    eventRadio(filterByPrice)
    eventRadio(sortName)
    eventRadio(sortPrice)

    $("input.filter-sort").on("change", function () {
        const brands = filterByBrand.filter(':checked').map((index, element) => $(element).val()).toArray();
        const priceRange = filterByPrice.filter(':checked').val();
        const sortNameValue = sortName.filter(':checked').val();
        const sortPriceValue = sortPrice.filter(':checked').val();
        const sortNewProductValue = sortNewProduct.filter(':checked').val();
        const display = $("#display-product");

        const data = {}
        if (brands.length > 0) data["brands"] = brands
        if (priceRange) data["priceRange"] = priceRange
        if (sortNameValue) data["sortByName"] = sortNameValue
        if (sortPriceValue) data["sortByPrice"] = sortPriceValue
        if (sortNewProductValue) data["sortByNewProduct"] = sortPriceValue

        $.ajax({
            url: `/stationery_kimi/api/products/booth/${categoryId}`,
            type: "GET",
            contentType: "application/json",
            data: data,
            success: function (response) {
                display.empty()
                if (response.data)
                    response.data.forEach(product => {
                        display.append(loadProduct(product))
                    });
            }
        })
    })
})

function eventRadio(element) {
    element.on("change", function (e) {
        element.not(this).prop("checked", false);

        if (!$(this).is(":checked"))
            $(this).prop("checked", false);
        else
            $(this).prop("checked", true);
    })
}

function loadProduct(product) {
    let htmlProduct =
        `<div class="card product rounded-4">
            <a href="/stationery_kimi/product/${product.id}" class="text-decoration-none">
                <div class="card-img overflow-hidden rounded-bottom-0 rounded-4">
                    <img style="width: 300px; height: 300px" src="/stationery_kimi${product.productImageDTO.url}" class="card-img-top" alt="san_pham.png">
                </div>
                <div class="card-body text-center bg-white py-2">
                    <div class="card-title m-0 position-relative">`

    if (product.productNew)
        htmlProduct += `<span class="position-absolute d-inline-block text-white bg-danger rounded px-1 position-absolute">New</span>
                        <h5 class="title d-inline-block text-secondary overflow-hidden mb-0" style="max-height: 45px; text-indent: 45px">${product.name}</h5>`
    else
        htmlProduct += `<h5 class="title d-inline-block text-secondary overflow-hidden mb-0" th:text="${name}"  style="max-height: 45px">${product.name}</h5>`
    htmlProduct += `</div>
                        <div class="card-text">`
    if (!product.discountPercent)
        htmlProduct += `<span class="new-price text-primary fs-5 fw-bold">${formatCurrency.format(product.price)}</span>`
    else htmlProduct += `<span class="new-price text-primary fs-5  fw-bold">
                            ${formatCurrency.format(product.price - product.discountPercent * product.price)}
                        </span>
                            <del class="old-price text-body-tertiary">${formatCurrency.format(product.price)}</del>`
    htmlProduct += `</div>
                    </div>
                </a>
                <div class="card-footer border-0 bg-white d-flex justify-content-between rounded-4">
                    <a href="/stationery_kimi/detail/${product.id}" class="text-decoration-none">`
    if (product.discountPercent)
        htmlProduct += `<div class="align-content-center">
                            <span class="tag bg-warning fs-6 d-inline-block text-white rounded ps-1 pe-3">
                                -${product.discountPercent * 100}%
                            </span>
                        </div>`
    htmlProduct += `</a>
                     <div>
                        <button data-url="/stationery_kimi/api/products/see-more/${product.id}"
                                class="see see-more-info-product border-0 bg-primary p-2 rounded-circle text-white align-content-end">
                            <i class="fa-sharp fa-solid fa-eye"></i>
                        </button>
                        <button data-url="/stationery_kimi/api/products/see-more/${product.id}"
                                class="add-to-cart see-more-info-product border-0 bg-danger p-2 rounded-circle text-white align-content-end">
                            <i class="fa-sharp fa-solid fa-cart-plus"></i>
                        </button>
                    </div>
                </div>
            </div>`

    return htmlProduct;
}

