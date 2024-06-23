package com.lamnguyen.stationery_kimi.controller.admin;

import com.lamnguyen.stationery_kimi.dto.*;
import com.lamnguyen.stationery_kimi.entity.Product;
import com.lamnguyen.stationery_kimi.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/products")
public class ProductManagerController {
    @Autowired
    private IProductService productService;

    @GetMapping("/get")
    public DatatableApiResponse<List<ProductManager>> get(@RequestParam(required = false) Map<String, Object> query) {
        DatatableApiRequest request = DatatableApiRequest.newInstance(query);
        List<ProductManager> product = productService.findAll(request);

        return DatatableApiResponse.<List<ProductManager>>builder()
                .data(product)
                .draw(request.getDraw())
                .recordsTotal(product.size())
                .recordsFiltered(product.size())
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<ProductDTO> addProduct(@ModelAttribute Product product) {
        ProductDTO productDTO = productService.addProduct(product);
        return ApiResponse.<ProductDTO>builder()
                .message("Thêm sản phẩm thành công!")
                .data(productDTO)
                .build();
    }

    @PutMapping("/update")
    public ApiResponse<ProductDTO> updateProduct(@ModelAttribute Product product) {
        ProductDTO productDTO = productService.updateProduct(product);
        return ApiResponse.<ProductDTO>builder()
                .message("Cập nhật sản phẩm thành công!")
                .data(productDTO)
                .build();
    }

    @PostMapping("/lock/{id}")
    public ApiResponse<ProductManager> lock(@PathVariable("id") Long id) {
        ProductManager result = productService.lock(id);
        return ApiResponse.<ProductManager>builder()
                .message("Khóa sản phẩm thành công!")
                .data(result)
                .build();
    }

    @PostMapping("/new/{id}")
    public ApiResponse<ProductManager> setNewProduct(@PathVariable("id") Long id) {
        ProductManager result = productService.setNewProduct(id);
        return ApiResponse.<ProductManager>builder()
                .message("Khóa sản phẩm thành công!")
                .data(result)
                .build();
    }
}
