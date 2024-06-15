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
    private String url;
    private String name;
}
