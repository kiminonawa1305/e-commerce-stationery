$(document).ready(function(){
    const states = $('.state[data-state]')

    $('header').load('layout/header.html');
    $('footer').load('layout/footer.html');
    $('.categories-info').load('layout/info_account.html');
    customStateElements(states)
})

/*
0. đã hủy (data-state = 0)
1. đang chờ xác nhận (data-state = 1)
2. đã xác nhận (data-state = 2)
3. đang giao hàng (data-state = 3)
4. đã nhận hàng (data-state = 4)
 */
function customStateElements(elements){
    elements.each(function(index, element){
        const state = parseInt($(element).attr('data-state'))
        const deletedIcon = $('<i class="fa-solid fa-trash"></i>')
        const waitingIcon = $('<i class="fa-solid fa-loader"></i>')
        const authenIcon = $('<i class="fa-solid fa-check"></i>')
        const shippingIcon = $('<i class="fa-solid fa-truck"></i>')
        const finishIcon = $('<i class="fa-solid fa-box-circle-check"></i>')


        switch (state) {
            case 0:
                $(element).css('background-color', '#f1aeb5').css('color', '#dc3545')
                $(element).append(deletedIcon)
                break
            case 1:
                $(element).css('background-color', '#e2e3e5').css('color', '#6c757d')
                $(element).append(waitingIcon)
                break
            case 2:
                $(element).css('background-color', '#ffe69c').css('color', '#ffc107')
                $(element).append(authenIcon)
                break
            case 3:
                $(element).css('background-color', '#9ec5fe').css('color', '#0d6efd')
                $(element).append(shippingIcon)
                break
            case 4:
                $(element).css('background-color', '#a3cfbb').css('color', '#198754')
                $(element).append(finishIcon)
                break
        }
    })
}