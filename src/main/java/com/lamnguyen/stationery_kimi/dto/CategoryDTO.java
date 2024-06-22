package com.lamnguyen.stationery_kimi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CategoryDTO implements Serializable {
    private Long id;
    private String name;
}
