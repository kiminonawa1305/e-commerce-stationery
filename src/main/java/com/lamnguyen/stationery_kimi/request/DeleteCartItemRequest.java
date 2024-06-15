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
public class DeleteCartItemRequest {
    @NotNull(message = "Cart item id không được để trống!")
    private String cartItemId;
}
