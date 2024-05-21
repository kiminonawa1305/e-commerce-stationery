package com.lamnguyen.stationery_kimi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProductDTO implements Serializable {
    private Long id;
    private String name;
    private Long quantity;
    private String type;
    private String description;
    private Integer price;
    private Boolean lock;
}
