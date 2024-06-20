package com.lamnguyen.stationery_kimi.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@SuperBuilder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDisplay extends BillDTO {
    private String date;
    private Integer totalPay;
}