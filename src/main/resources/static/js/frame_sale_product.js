const menu_item = $('.frame_sale_product .menu-item');
menu_item.on('click', function (event) {
    menu_item.removeClass('active')
    $(this).addClass('active')
})