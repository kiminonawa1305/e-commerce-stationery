$(document).ready(event => {
    $(".see-more-info-product").click(function (event) {
        const url = this.getAttribute("data-url");
        $.ajax({
            url: url,
            type: "GET",
            typeContent: "html",
            success: response => {
                $("#see-more").html(response);
            }
        });
    });
});