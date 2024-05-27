$(document).ready(function () {
    cloneAccordionItem()
    addEventAccordion()
    showAccordionActive()
    closeAccordionMain()
})

function addEventAccordion() {
    const coupons = $('.coupon-item')
    coupons.each(function (index) {
        $(this).find(".accordion-collapse").each(function () {
            $(this).attr('id', `penalStayOpen-${index}`)
        });
        $(this).find(".accordion-button").each(function () {
            $(this).attr('data-bs-target', `#penalStayOpen-${index}`)
            $(this).attr('aria-controls', `penalStayOpen-${index}`)
        });
        $(this).find(".apply_coupon_btn").each(function () {
            $(this).attr('data-id', `apply-coupon-${index}`)
        });
    });
}

function cloneAccordionItem() {
    for (let i = 0; i < 5; i++) {
        const cloneCoupon = $('.coupon-item:first').clone()
        if ($('.list-coupons > .coupon-item').length < 2) {
            $('.list-coupons > .coupon-item').before(cloneCoupon)
        } else {
            $('#hidden-coupon > .accordion-body').append(cloneCoupon)
        }
    }
}

function showAccordionActive() {
    $('.accordion-button').click(function () {
        if ($(this).hasClass('active')) {
            $(this).removeClass('active')
            $(this).addClass('collapsed')
            $(this).attr('aria-expanded', 'false')
            $(this).attr('data-bs-toggle', 'collapse')
            if ($(this).attr('class').indexOf('see-more-btn') >= 0) {
                $(this).text('Xem thêm')
                $(this).parents('.hidden-list-coupons').find('#hidden-coupon').removeClass('show')
                return
            }
            $(this).parents('.visibility-item').find('.accordion-collapse').removeClass('show')

        } else {
            $(this).addClass('active')
            $(this).attr('data-bs-toggle', '')
            if ($(this).attr('class').indexOf('see-more-btn') >= 0) $(this).text('Rút gọn')
        }
        $(this).toggleClass('changed')
    })
}

function closeAccordionMain() {
    const closeAccordion = $('.btn-close[aria-label]')
    const seeMoreBtn = $('.see-more-btn')
    closeAccordion.click(function () {
        seeMoreBtn.text('Xem thêm')
        seeMoreBtn.removeClass('active')
        seeMoreBtn.addClass('collapsed')
        seeMoreBtn.attr('aria-expanded', 'false')
        seeMoreBtn.parents('.hidden-list-coupons').find('.accordion-collapse').removeClass('show')
        seeMoreBtn.attr('data-bs-toggle', 'collapse')
    })
}