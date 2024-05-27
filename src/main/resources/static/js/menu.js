$(document).ready(function (event) {
    const ARROW = `<i class="fa-solid fa-chevron-right"></i>`;
    const menuItem = $('.menu-item');
    menuItem.has('.menu-list').append(ARROW);

    $('.menu').mouseleave(function (event) {
        $(this).removeClass("hover")
    })

    $('.button-show-menu').hover(function (event) {
        const menu = $(this).parent();
        menu.addClass("hover")
    })
})