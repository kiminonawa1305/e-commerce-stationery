package com.lamnguyen.stationery_kimi.controller.admin;

import com.lamnguyen.stationery_kimi.dto.*;
import com.lamnguyen.stationery_kimi.service.IProductImageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/product-images")
public class ProductImageManagerRestController {
    private static final Logger log = LoggerFactory.getLogger(ProductImageManagerRestController.class);
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

    @PostMapping("/add/{productId}")
    public ApiResponse<ProductDTO> add(@PathVariable("productId") Long productId, @RequestParam(required = false) Map<String, Object> query) {
//        ProductDTO productDTO = iProductImageService.addProduct(product);
        System.out.println(query);
        return ApiResponse.<ProductDTO>builder()
                .message("Thêm sản phẩm thành công!")
//                .data(productDTO)
                .build();
    }

    @PutMapping("/edit/{productImageId}")
    public EditDataTableResponse<ProductImageDTO> update(@PathVariable("productImageId") Long id, @RequestParam(required = false) Map<String, Object> query) {
//        ProductDTO productDTO = iProductImageService.updateProduct(product);
        return EditDataTableResponse.<ProductImageDTO>builder()
//                .data(result)
                .build();
    }


    @DeleteMapping("/delete/{productImageId}")
    public EditDataTableResponse<ProductImageDTO> delete(@PathVariable("productImageId") Long id) {
//        ProductManager result = iProductImageService.setNewProduct(id);
        return EditDataTableResponse.<ProductImageDTO>builder()
//                .data(result)
                .build();
    }
}
