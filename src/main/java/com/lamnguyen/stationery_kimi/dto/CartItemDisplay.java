package com.lamnguyen.stationery_kimi.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItemDisplay implements Serializable {
    private String cartItemId;
    private Integer quantity;
    private String option;
    private Integer price;
    private Double percentDiscount;
    private String url;
    private String name;

    public Integer getTotalPrice() {
        return price * quantity;
    }

    public Integer getTotalPay() {
        return getTotalPrice() - getTotalDiscount();
    }

    public Integer getTotalDiscount() {
        return (int) (percentDiscount * price) * quantity;
    }
}
