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
public class AddCartItemRequest {

    @NotNull(message = "Id không được để trống!")
    private Long productId;

    @NotNull(message = "Quantity không được để trống!")
    private Integer quantity;

    @NotNull(message = "ProductOptionId không được để trống!")
    private Long productOptionId;
}
