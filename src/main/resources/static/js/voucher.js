$(document).ready(function (event) {
    $('.voucher-button-show-condition').click(function (event) {
        const parent = $(this).parents('.voucher');
        if (parent.hasClass('show-condition'))
            parent.removeClass('show-condition');
        else parent.addClass('show-condition');
    })
})