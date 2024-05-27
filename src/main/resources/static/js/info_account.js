$(document).ready(function(){
    const menuItems = $('.menu-personal__items .item')
    addEventClick(menuItems)
})

function addEventClick(items) {
    items.on('click',function (){
        items.removeClass('active')
        $(this).addClass('active')
    })
}