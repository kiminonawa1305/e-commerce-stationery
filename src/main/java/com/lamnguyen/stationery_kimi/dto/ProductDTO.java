package com.lamnguyen.stationery_kimi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;
import java.util.List;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO implements Serializable {
    private Integer id;
    private String name;
    private Integer price;
    private Double discountPercent;
    private Long quantity;
    private String type;
    private String description;
    private List<String> urlImageProducers;
    private List<ProductOptionDTO> productOptionDTOS;
}
