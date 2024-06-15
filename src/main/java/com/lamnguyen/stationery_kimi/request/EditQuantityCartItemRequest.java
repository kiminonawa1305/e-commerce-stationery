package com.lamnguyen.stationery_kimi.request;


import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class EditQuantityCartItemRequest {

    @NotNull(message = "Cart item id không được để trống!")
    private String cartItemId;

    @NotNull(message = "Quantity không được để trống!")
    private Integer quantity;
}
