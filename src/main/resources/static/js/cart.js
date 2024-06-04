$(document).ready(function () {
    let forms = $('.needs-validation');
    // Loop over them and prevent submission
    forms.on('submit', function (event) {
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
});