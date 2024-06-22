package com.lamnguyen.stationery_kimi.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DatatableApiResponse<T> implements Serializable {
    @Builder.Default
    private Integer draw = 1;
    private Integer recordsTotal, recordsFiltered;

    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private T data;
}
