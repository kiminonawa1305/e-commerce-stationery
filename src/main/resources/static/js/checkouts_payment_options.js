$(document).ready(function(){
    loadDisplayInfoPayment()
    onlyOneCheckRadio()
})
function loadDisplayInfoPayment() {
    $('.payment_frame').load('layout/display_info_payments.html')
}

function onlyOneCheckRadio() {
    let checkRadios = $('.payment-options input[type="checkbox"]')
    checkRadios.change(function() {
        if ($(this).prop("checked")) {
            // Tắt tất cả các checkbox trong nhóm
            $(".checkradio[name='" + $(this).attr("name") + "']").not($(this)).prop("checked", false);
        }
    });
}