package com.lamnguyen.stationery_kimi.enums;

public enum BillStatusEnum {
    ALL("Tất cả"),
    ORDERED("Đã đặt hàng"),
    ON_THE_WAY("Đang giao hàng"),
    ARRIVED_AT_THE_WAREHOUSE("Đã về kho"),
    DELIVERED("Đã giao hàng"),
    CANCELED("Đã hủy");

    private String status;

    BillStatusEnum(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
