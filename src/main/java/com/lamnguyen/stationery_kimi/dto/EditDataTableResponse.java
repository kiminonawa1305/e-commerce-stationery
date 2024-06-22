package com.lamnguyen.stationery_kimi.dto;

import lombok.Builder;

@Builder
public record EditDataTableResponse<T>(T data) {
}