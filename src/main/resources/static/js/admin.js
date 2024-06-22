$(document).ready(function (event) {
    if (!location.hash) location.hash = "#dashboard"
    const menuItems = $('.menu-item-manager'),
        frames = $('.frame'),
        dashboard = $("#dashboard"),
        productManager = $("#product-manager"),
        billManager = $("#bill-manager"),
        accountManager = $("#account-manager"),
        categoryManager = $("#category-manager")


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
                break;
            case "account":
                break;
            case "category":
                loadCategory()
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
            url: '/stationery_kimi/admin/api/categories/get',
        },
        fields: [
            {
                label: 'Name:',
                name: 'last_name'
            },
        ],
        table: '#category-manager-table'
    });

    return new DataTable('#category-manager-table', {
        ajax: {
            url: '/stationery_kimi/admin/api/categories/get',
        },
        columns: [
            {
                title: 'ID',
                name: 'id',
                data: 'id',
            },
            {
                title: 'Name',
                name: 'name',
                data: 'name',
            },
            {
                title: 'Tùy chọn',
                data: null,
                orderable: false,
                render: function (data, type) {
                    return `<button data-category-id="${data.id}" class="btn btn-primary">Sửa</button>`;
                }
            }
        ],
        processing: true,
        serverSide: true,
        search: true,
        select: true
    });
}
