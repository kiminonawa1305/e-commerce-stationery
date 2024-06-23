const formatCurrency = new Intl.NumberFormat('vi-VN', {
        style: 'currency', currency: 'VND'
    }),
    formatDateTime = new Intl.DateTimeFormat('vi-VN', {dateStyle: 'short'}),
    htmlTableBillStatus = ` <table id="bill-status-manager-table"
                                           class="display row-border table table-striped border border-bottom-0 border-1 border-secondary rounded-2 overflow-hidden"
                                           style="width:100%">
                                    </table>`

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

    const dataTable = new DataTable('#category-manager-table', {
        ajax: {
            url: '/stationery_kimi/admin/api/categories/get',
        }, columns: [
            {
                title: 'ID: ', name: 'id', data: 'id',
            },
            {
                title: 'Tên: ', name: 'name', data: 'name',
            },
            {
                title: 'Tổng số lượng sản phẩm: ', name: 'totalProduct', data: 'totalProduct',
            },
            {
                title: 'Khóa: ', name: 'lock', data: null, render: function (data, type, row) {
                    if (data.lock) {
                        return `<button class="btn btn-sm btn-warning btn-lock" data-value="unlock" data-id="${row.id}">
                                    <i class="fa-solid fa-lock"></i>
                                </button>`;
                    } else return `<button class="btn btn-sm btn-warning btn-lock" data-value="lock" data-id="${row.id}">
                                    <i class="fa-solid fa-lock-open"></i>
                                </button>`;
                }
            }
        ], layout: {
            topStart: {
                buttons: [{extend: 'create', editor: editor}, {extend: 'edit', editor: editor},]
            }
        }, processing: true, serverSide: true, search: true, select: true
    });

    dataTable.on('click', 'button.btn-lock', function () {
        let categoryId = $(this).attr('data-id');
        $.ajax({
            url: `/stationery_kimi/admin/api/categories/lock/${categoryId}`,
            type: 'POST',
            success: function (response) {
                Toastify({
                    text: "Thành công!", duration: 1000, "backgroundColor": "#4cea06"
                }).showToast()
                dataTable.ajax.reload();
            },
            error: function (err) {
                Toastify({
                    text: err.response.message, duration: 1000, backgroundColor: "#ea0606"
                }).showToast();
            }
        });
    });

    return dataTable;
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


    dataTable.on('click', 'button.btn-lock', function () {
        let accountId = $(this).attr('data-id');
        $.ajax({
            url: `/stationery_kimi/admin/api/accounts/lock/${accountId}`, type: 'POST', success: function (response) {
                Toastify({
                    text: "Thành công!", duration: 1000, "backgroundColor": "#4cea06"
                }).showToast()
                dataTable.ajax.reload();
            }, error: function (err) {
                Toastify({
                    text: err.response.message, duration: 1000, backgroundColor: "#ea0606"
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

const getJson = (d) => {
    let json = {};
    $.each(d.data, function (key, value) {
        $.each(value, function (field, fieldValue) {
            json[field] = fieldValue; // append các trường dữ liệu
        });
    });
    return JSON.stringify(json);
}


const loadBills = () => {
    const dataTable = new DataTable('#bill-manager-table', {
        ajax: {
            url: '/stationery_kimi/admin/api/bills/get',
        }, columns: [{
            className: 'dt-control', orderable: false, data: null, defaultContent: ''
        }, {
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
                return formatCurrency.format(data.totalDiscount)
            }
        }, {
            title: 'Tổng số tiền thanh toán: ', name: 'totalPay', data: null, render: function (data, type, row) {
                return formatCurrency.format(data.totalPay)
            }
        }, {
            title: 'Cập nhật trạng thái hóa đơn: ', data: null, orderable: false, render: function (data, type, row) {
                return `<button class="btn btn-sm btn-warning btn-update-status" data-id="${row.id}">Cập nhật trạng thái đơn hàng</button>`
            }
        },], processing: true, serverSide: true, search: true, select: true, scrollX: true
        , createdRow: function (row, data, dataIndex) {
            if (data.cancel) {
                $(row).css('background-color', '#ea0808');
                $(row).find("td").css("background", "none").css("color", "white");
                return
            }

            if (data.success) {
                $(row).css('background-color', '#2ef50a');
                $(row).find("td").css("background", "none").css("color", "white");
            }
        },
    });

    // Add event listener for opening and closing details
    dataTable.on('click', 'td.dt-control', function (e) {
        let tr = e.target.closest('tr');
        let row = dataTable.row(tr);
        const id = row.data().id;

        $.ajax({
            url: `/stationery_kimi/admin/api/bills/bill-detail/${id}`,
            method: "GET",
            contentType: "application/json",
            success: function (response) {
                if (row.child.isShown()) {
                    row.child.hide();
                } else {
                    // Open this row
                    row.child(renderBillDetail(response.data)).show();
                }
            },
            error: function (e) {

            }
        })
    });

    dataTable.on("click", "button.btn-update-status", function (e) {
        const modal = $("#modal-update-bill-status");
        modal.modal("show");
        const billId = $(this).attr("data-id");
        modal.find(".modal-body").empty().append(htmlTableBillStatus);
        loadBillStatus(billId);
    });

    return dataTable;
}

const renderBillDetail = (data) => {
    let table = `<table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>ID</th>
                            <th>Tên sản phẩm</th>
                            <th>Số lượng</th>
                            <th>Giá</th>
                        </tr>
                    </thead>
                    <tbody>`;

    data.forEach((item) => {
        table += `<tr>
                    <td>${item.id}</td>
                    <td>${item.name}</td>
                    <td>${item.quantity}</td>
                    <td>${formatCurrency.format(item.price)}</td>
                  </tr>`;
    });

    table += `</tbody></table>`;

    return table;
}

const loadBillStatus = (billId) => {
    const editor = new DataTable.Editor({
        ajax: {
            create: {
                type: 'POST',
                url: `/stationery_kimi/admin/api/bill-statuses/create/${billId}`,
                contentType: "application/json",
                processData: false,
                data: function (d) {
                    return getJson(d);
                }
            }, edit: {
                type: 'PUT', url: '/stationery_kimi/admin/api/bill-statuses/edit/_id_',
                contentType: 'application/json',
                data: function (d) {
                    return getJson(d);
                }
            }, remove: {
                type: 'DELETE', url: '/stationery_kimi/admin/api/bill-statuses/delete/_id_'
            }
        }, fields: [
            {
                label: 'Trạng thái:', name: 'status',
                type: 'select',
                options: [
                    {label: 'Đã đặt hàng', value: 'ORDERED'},
                    {label: 'Đã đến kho', value: 'ARRIVED_AT_THE_WAREHOUSE'},
                    {label: 'Đang trên đường giao', value: 'ON_THE_WAY'},
                    {label: 'Đã giao hàng', value: 'DELIVERED'},
                    {label: 'Đã hủy', value: 'CANCELED'}
                ]
            },
            {
                label: 'Mô tả: ', name: 'description', type: "textarea"
            },
            {
                label: 'Thời gian: ', name: 'date', type: 'datetime', format: 'YYYY-MM-DD HH:mm:ss'
            },
        ], idSrc: 'id', table: '#bill-status-manager-table'
    });


    const dataTable = new DataTable('#bill-status-manager-table', {
        ajax: {
            url: `/stationery_kimi/admin/api/bill-statuses/get/${billId}`,
        }, columns: [
            {
                title: 'ID: ', name: 'id', data: 'id',
            },
            {
                title: 'Trạng thái: ', name: 'status', data: 'status',
            },
            {
                title: 'Mô tả: ', name: 'description', data: 'description', orderable: false,
            },
            {
                title: 'Thời gian: ', name: 'date', data: null, render: function (data, type, row) {
                    return moment(data.date).format('DD/MM/YYYY HH:mm:ss');
                }
            },
        ], processing: true, serverSide: true, search: true, select: true,
        layout: {
            topStart: {
                buttons: [
                    {extend: 'create', editor: editor},
                    {extend: 'edit', editor: editor},
                    {extend: 'remove', editor: editor},
                ]
            }
        }
    });

    return dataTable;
}
