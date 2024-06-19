package com.lamnguyen.stationery_kimi.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDTO {
    private Integer id;
    private String name,
            phone,
            email,
            fullAddress,
            payment;
}