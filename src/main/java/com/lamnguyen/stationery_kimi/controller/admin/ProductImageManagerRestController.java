package com.lamnguyen.stationery_kimi.controller.admin;

import com.lamnguyen.stationery_kimi.dto.*;
import com.lamnguyen.stationery_kimi.entity.Product;
import com.lamnguyen.stationery_kimi.service.IProductImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/product-images")
public class ProductImageManagerRestController {
    @Autowired
    private IProductImageService iProductImageService;

    @GetMapping("/get/{productId}")
    public DatatableApiResponse<List<ProductImageDTO>> get(@PathVariable("productId") Long productId, @RequestParam(required = false) Map<String, Object> query) {
        DatatableApiRequest request = DatatableApiRequest.newInstance(query);
        List<ProductImageDTO> productImages = iProductImageService.findByProductId(productId);

        return DatatableApiResponse.<List<ProductImageDTO>>builder()
                .data(productImages.stream().skip(request.getStart()).limit(request.getLength()).toList())
                .draw(request.getDraw())
                .recordsTotal(productImages.size())
                .recordsFiltered(productImages.size())
                .build();
    }

    @PostMapping("/add")
    public ApiResponse<ProductDTO> addProduct(@ModelAttribute Product product, @RequestParam(required = false) Map<String, Object> query) {
//        ProductDTO productDTO = iProductImageService.addProduct(product);
        return ApiResponse.<ProductDTO>builder()
                .message("Thêm sản phẩm thành công!")
//                .data(productDTO)
                .build();
    }

    @PutMapping("/update")
    public ApiResponse<ProductDTO> updateProduct(@ModelAttribute Product product) {
//        ProductDTO productDTO = iProductImageService.updateProduct(product);
        return ApiResponse.<ProductDTO>builder()
                .message("Cập nhật sản phẩm thành công!")
//                .data(productDTO)
                .build();
    }

    @PostMapping("/lock/{id}")
    public ApiResponse<ProductManager> lock(@PathVariable("id") Long id) {
//        ProductManager result = iProductImageService.lock(id);
        return ApiResponse.<ProductManager>builder()
                .message("Khóa sản phẩm thành công!")
//                .data(result)
                .build();
    }

    @PostMapping("/new/{id}")
    public ApiResponse<ProductManager> setNewProduct(@PathVariable("id") Long id) {
//        ProductManager result = iProductImageService.setNewProduct(id);
        return ApiResponse.<ProductManager>builder()
                .message("Khóa sản phẩm thành công!")
//                .data(result)
                .build();
    }
}
