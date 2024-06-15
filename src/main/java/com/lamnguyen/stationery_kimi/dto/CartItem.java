package com.lamnguyen.stationery_kimi.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CartItem {
    private Long productId;
    private Long productOptionId;
    private Integer quantity;


    public String getCartItemId() {
        return this.productId + "-" + this.productOptionId;
    }

    public static String getCartItemId(Long productId, Long productOptionId) {
        return productId + "-" + productOptionId;
    }
}
