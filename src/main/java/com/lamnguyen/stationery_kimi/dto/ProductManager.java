package com.lamnguyen.stationery_kimi.dto;

import com.lamnguyen.stationery_kimi.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * DTO for {@link Product}
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ProductManager implements Serializable {
    private Long id;
    private String name;
    private String description;
    private Integer price;
    private Boolean productNew;
    private String brand;
    private Boolean lock;
    private Integer totalOption;
    private Integer totalImage;
}