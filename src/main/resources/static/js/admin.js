const format = new Intl.NumberFormat('vi-VN', {
    style: 'currency',
    currency: 'VND'
})

$(document).ready(function (event) {
    let dataTableBill, dataTableAccount, dataTableCategory, dataTableProduct;

    if (!location.hash) location.hash = "#dashboard"
    const menuItems = $('.menu-item-manager'), frames = $('.frame'), dashboard = $("#dashboard"),
        productManager = $("#product-manager"), billManager = $("#bill-manager"),
        accountManager = $("#account-manager"), categoryManager = $("#category-manager")


    menuItems.on("mouseenter", function (e) {
        menuItems.not(".active").removeClass("bg-primary").addClass("bg-warning");
        $(this).addClass("bg-primary").removeClass("bg-warning");
    })
    menuItems.on("mouseleave", function (e) {
        $(this).not(".active").removeClass("bg-primary").addClass("bg-warning");
    })

    menuItems.on("click", function (e) {
        if ($(this).hasClass("active")) return
        const frameId = $(this).attr("data-frame");
        const title = $(this).text()
        frames.hide()
        frames.eq(frameId - 1).show()
        menuItems.removeClass("active").removeClass("bg-primary").addClass("bg-warning")
        $(this).addClass("active").addClass("bg-primary").removeClass("bg-warning")
        $("title").text(title)
        const manager = $(this).attr("data-manager")
        switch (manager) {
            case "product":
                break;
            case "bill":
                if (dataTableBill) dataTableBill.ajax.reload()
                else dataTableBill = loadBills()
                break;
            case "account":
                if (dataTableAccount) dataTableAccount.ajax.reload()
                else dataTableAccount = loadAccount()
                break;
            case "category":
                if (dataTableCategory) dataTableCategory.ajax.reload()
                else dataTableCategory = loadCategory()
                break;
            default:
        }
    })


    const hook = location.hash.replace("#", "");
    $(`.menu-item-manager[data-hook=${hook}]`).click()
});

const loadCategory = () => {
    const editor = new DataTable.Editor({
        ajax: {
            create: {
                type: 'POST',
                url: '/stationery_kimi/admin/api/categories/create',
                contentType: false,
                processData: false,
                data: function (d) {
                    return getFromData(d);
                }
            }, edit: {
                type: 'PUT', url: '/stationery_kimi/admin/api/categories/edit/_id_', contentType: 'application/json', // Đặt Content-Type là application/json
                data: function (d) {
                    return JSON.stringify(d.data); // Chuyển dữ liệu sang JSON
                }
            }, remove: {
                type: 'DELETE', url: '/stationery_kimi/admin/api/categories/delete/_id_'
            }
        }, fields: [{
            label: 'Name:', name: 'name'
        },], idSrc: 'id', table: '#category-manager-table'
    });

    return new DataTable('#category-manager-table', {
        ajax: {
            url: '/stationery_kimi/admin/api/categories/get',
        }, columns: [{
            title: 'ID: ', name: 'id', data: 'id',
        }, {
            title: 'Tên: ', name: 'name', data: 'name',
        }, {
            title: 'Tổng số lượng sản phẩm: ', name: 'totalProduct', data: 'totalProduct',
        },], layout: {
            topStart: {
                buttons: [{extend: 'create', editor: editor}, {extend: 'edit', editor: editor},]
            }
        }, processing: true, serverSide: true, search: true, select: true
    });
}

const loadAccount = () => {
    const dataTable = new DataTable('#account-manager-table', {
        ajax: {
            url: '/stationery_kimi/admin/api/accounts/get',
        }, columns: [{
            title: 'ID: ', name: 'id', data: 'id',
        }, {
            title: 'Họ: ', name: 'lastName', data: 'lastName',
        }, {
            title: 'Tên: ', name: 'firstName', data: 'firstName',
        }, {
            title: 'Email: ', name: 'email', data: 'email',
        }, {
            title: 'Số điện thoại: ', name: 'phone', data: 'phone',
        }, {
            title: 'Quyền: ', name: 'role', data: 'role',
        }, {
            title: 'Khóa: ', name: 'lock', data: null, render: function (data, type, row) {
                if (data.lock) {
                    return `<button class="btn btn-sm btn-warning btn-lock" data-value="unlock" data-id="${row.id}">
                                    <i class="fa-solid fa-lock"></i>
                                </button>`;
                } else return `<button class="btn btn-sm btn-warning btn-lock" data-value="lock" data-id="${row.id}">
                                    <i class="fa-solid fa-lock-open"></i>
                                </button>`;
            }
        },], processing: true, serverSide: true, search: true, select: true, scrollX: true,
    });


    $('#account-manager-table').on('click', 'button.btn-lock', function () {
        let accountId = $(this).attr('data-id');
        $.ajax({
            url: `/stationery_kimi/admin/api/accounts/lock/${accountId}`,
            type: 'POST',
            success: function (response) {
                Toastify({
                    text: "Thành công!",
                    duration: 1000,
                    "backgroundColor": "#4cea06"
                }).showToast()
                $('#account-manager-table').DataTable().ajax.reload();
            },
            error: function (err) {
                Toastify({
                    text: err.response.message,
                    duration: 1000,
                    backgroundColor: "#ea0606"
                }).showToast();
            }
        });
    });

    return dataTable;
}

const getFromData = (d) => {
    let formData = new FormData();
    $.each(d.data, function (key, value) {
        $.each(value, function (field, fieldValue) {
            formData.append(field, fieldValue); // append các trường dữ liệu
        });
    });
    return formData;
}


const loadBills = () => {
    const dataTable = new DataTable('#bill-manager-table', {
        ajax: {
            url: '/stationery_kimi/admin/api/bills/get',
        }, columns: [{
            title: 'ID: ', name: 'id', data: 'id',
        }, {
            title: 'User ID: ', name: 'userId', data: 'userId',
        }, {
            title: 'Tên khách hàng: ', name: 'name', data: 'name',
        }, {
            title: 'Email: ', name: 'email', data: 'email',
        }, {
            title: 'Số điện thoại: ', name: 'phone', data: 'phone',
        }, {
            title: 'Địa chỉ giao hàng: ', name: 'shippingAddress', data: 'shippingAddress', orderable: false,
        }, {
            title: 'Hình thức thanh toán: ', name: 'paymentMethod', data: 'paymentMethod', orderable: false,
        }, {
            title: 'Tổng số tiền được giảm: ', name: 'totalDiscount', data: null, render: function (data, type, row) {
                return format.format(data.totalDiscount)
            }
        }, {
            title: 'Tổng số tiền thanh toán: ', name: 'totalPay', data: null, render: function (data, type, row) {
                return format.format(data.totalPay)
            }
        },], processing: true, serverSide: true, search: true, select: true, scrollX: true,
    });

    return dataTable;
}
