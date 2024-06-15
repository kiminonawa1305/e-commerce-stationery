package com.lamnguyen.stationery_kimi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DiscountDTO {
    private Long id;
    private Double discountPercent;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}
