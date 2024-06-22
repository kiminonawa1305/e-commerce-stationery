package com.lamnguyen.stationery_kimi.controller.admin;

import com.lamnguyen.stationery_kimi.dto.ApiResponse;
import com.lamnguyen.stationery_kimi.dto.CategoryDTO;
import com.lamnguyen.stationery_kimi.dto.DatatableApiRequest;
import com.lamnguyen.stationery_kimi.dto.DatatableApiResponse;
import com.lamnguyen.stationery_kimi.entity.Product;
import com.lamnguyen.stationery_kimi.service.ICategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;


@RestController
@RequestMapping("/admin/api/categories")
public class CategoryManagerRestController {
    @Autowired
    private ICategoryService iCategoryService;

    @GetMapping("/get")
    public DatatableApiResponse<List<CategoryDTO>> get(@RequestParam(required = false) Map<String, Object> query) {
        DatatableApiRequest request = DatatableApiRequest.newInstance(query);
        List<CategoryDTO> categories = iCategoryService.findAll(request);

        return DatatableApiResponse.<List<CategoryDTO>>builder()
                .data(categories)
                .draw(request.getDraw())
                .recordsTotal(categories.size())
                .recordsFiltered(categories.size())
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<CategoryDTO> add(@ModelAttribute Product product) {
        return ApiResponse.<CategoryDTO>builder()
                .message("Cập nhật sản phẩm thành công!")
                .build();
    }

    @PutMapping("/update")
    public ApiResponse<CategoryDTO> update(@ModelAttribute Product product) {
        return ApiResponse.<CategoryDTO>builder()
                .message("Cập nhật sản phẩm thành công!")
                .build();
    }

    @PutMapping("/lock")
    public ApiResponse<CategoryDTO> lock(@ModelAttribute Product product) {
        return ApiResponse.<CategoryDTO>builder()
                .message("Khóa sản phẩm thành công!")
                .build();
    }
}
