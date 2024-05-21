package com.lamnguyen.stationery_kimi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

import java.io.Serializable;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ProviderDTO implements Serializable {
    private Long id;
    private String name;
    private String address;
    private String phone;
    private Boolean lock;
}
