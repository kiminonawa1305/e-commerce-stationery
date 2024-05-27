
$(document).ready(function () {
    $("cart_item").load("./component/cart_item.html");
    let forms = $('.needs-validation');

    // Loop over them and prevent submission
    forms.on('submit', function(event) {
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


    // Dữ liệu product mẫu
    let productList = [
        {id: 1, name: "Product 1", newPrice: 100000, oldPrice: 120000, tag: "-20%"},
        {id: 2, name: "Product 2", newPrice: 150000, oldPrice: 180000, tag: "-15%"},
        {id: 3, name: "Product 3", newPrice: 200000, oldPrice: 250000, tag: "-10%"},
        {id: 4, name: "Product 4", newPrice: 120000, oldPrice: 140000, tag: "-25%"},
        {id: 5, name: "Product 5", newPrice: 180000, oldPrice: 200000, tag: "-10%"},
        {id: 6, name: "Product 6", newPrice: 220000, oldPrice: 250000, tag: "-12%"},
        {id: 7, name: "Product 7", newPrice: 130000, oldPrice: 150000, tag: "-13%"},
        {id: 8, name: "Product 8", newPrice: 170000, oldPrice: 200000, tag: "-15%"},
        {id: 9, name: "Product 9", newPrice: 190000, oldPrice: 220000, tag: "-13%"},
        {id: 10, name: "Product 10", newPrice: 250000, oldPrice: 280000, tag: "-10%"},
        {id: 11, name: "Product 11", newPrice: 250000, oldPrice: 280000, tag: "-10%"}
    ];
    const carouselInner = $('.carousel-inner');
    let startIndex = 0;


    function createCarousel(productList) {
        carouselInner.empty();

        // Tính số lượng carousel được tạo ra
        let numCarousel = productList.length/5;
        if (numCarousel > Math.round(numCarousel)) {
            numCarousel = Math.round(numCarousel) + 1;
        }

        for (let i = 0; i < numCarousel; i++) {
            let carousel = $('<div>').addClass('carousel-item');
            let productDiv = $('<div>').addClass('product-list');
            let count = 0;
            for (let j = i * 5; j < productList.length && count < 5; j++, count++) {
                let productCard = `<product data-id=${productList[j].length}></product>`;
                productDiv.append(productCard);
            }
            carousel.append(productDiv);
            carouselInner.append(carousel);
        }
        $("product").load("./component/product.html");

        setTimeout(()=>{
            updateProduct();
            $('.carousel-item').first().addClass('active');
        }, 500);
    }
    createCarousel(productList);

    function updateProduct() {
        let products =  $(".product");
        products.each((index,item)=>{
            let currentItem = $(item);
            currentItem.find(".title").text(productList[startIndex + index].name);
            currentItem.find(".new-price").text(productList[startIndex + index].newPrice);
            currentItem.find(".old-price").text(productList[startIndex + index].oldPrice);
            currentItem.find(".tag").text(productList[startIndex + index].tag);
        })
    }
});