package com.lamnguyen.stationery_kimi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private Long id;
    private Integer totalDiscount;
    private String name,
            phone,
            email,
            shippingAddress,
            paymentMethod;
}