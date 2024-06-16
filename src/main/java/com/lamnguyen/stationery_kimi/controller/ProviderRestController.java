package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.dto.ProviderDTO;
import com.lamnguyen.stationery_kimi.exception.ApplicationException;
import com.lamnguyen.stationery_kimi.exception.ErrorCode;
import com.lamnguyen.stationery_kimi.response.ApiResponse;
import com.lamnguyen.stationery_kimi.service.IProviderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/providers")
public class ProviderRestController {
    @Autowired
    private IProviderService providerService;

    @GetMapping("/page-{page}")
    public ApiResponse<List<ProviderDTO>> findAllByLockFalse(@PathVariable("page") Integer page) {
        List<ProviderDTO> result = providerService.findAllByLockFalse(page);
        if (result.isEmpty()) throw new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND);
        return ApiResponse.<List<ProviderDTO>>builder()
                .message("Thành công!")
                .data(result)
                .build();
    }
}
