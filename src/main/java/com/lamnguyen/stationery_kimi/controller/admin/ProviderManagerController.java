package com.lamnguyen.stationery_kimi.controller.admin;

import com.lamnguyen.stationery_kimi.dto.ProviderDTO;
import com.lamnguyen.stationery_kimi.entity.Provider;
import com.lamnguyen.stationery_kimi.response.ApiResponse;
import com.lamnguyen.stationery_kimi.service.IProviderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/admin/providers")
public class ProviderManagerController {
    @Autowired
    private IProviderService providerService;

    @PostMapping("/add")
    public ApiResponse<ProviderDTO> addProvider(@ModelAttribute Provider provider) {
        ProviderDTO result = providerService.addProvider(provider);
        return ApiResponse.<ProviderDTO>builder()
                .message("Thêm thanh công!")
                .data(result)
                .build();
    }

    @PutMapping("/update")
    public ApiResponse<ProviderDTO> updateProvider(@ModelAttribute @Valid Provider provider) {
        ProviderDTO result = providerService.updateProvider(provider);
        return ApiResponse.<ProviderDTO>builder()
                .message("Cập nhật thanh công!")
                .data(result)
                .build();
    }

    @PutMapping("/lock")
    public ApiResponse<ProviderDTO> lockProvider(@ModelAttribute @Valid Provider provider) {
        ProviderDTO result = providerService.lockProvider(provider);
        return ApiResponse.<ProviderDTO>builder()
                .message("Cập nhật thanh công!")
                .data(result)
                .build();
    }

    @GetMapping("/page-{page}")
    public ApiResponse<List<ProviderDTO>> findAll(@PathVariable Integer page) {
        List<ProviderDTO> result = providerService.findAll(page);
        return ApiResponse.<List<ProviderDTO>>builder()
                .message("Lấy dữ liệu thành công!")
                .data(result)
                .build();
    }
}
