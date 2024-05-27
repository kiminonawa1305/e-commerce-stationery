$(document).ready(function(){
    loadCoupon()
    addEventForShortCoupon()
})

function loadCoupon() {
        $(`.vouchers`).load('../component/list_of_vouchers.html')
}

function addEventForShortCoupon() {
    $('.short_coupon').click(function(){
        $('.btn-show-sale').trigger('click')
    })
}