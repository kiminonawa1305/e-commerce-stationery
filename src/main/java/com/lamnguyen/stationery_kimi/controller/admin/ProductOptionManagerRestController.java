package com.lamnguyen.stationery_kimi.controller.admin;

import com.lamnguyen.stationery_kimi.dto.DatatableApiRequest;
import com.lamnguyen.stationery_kimi.dto.DatatableApiResponse;
import com.lamnguyen.stationery_kimi.dto.ProductOptionDTO;
import com.lamnguyen.stationery_kimi.entity.ProductOption;
import com.lamnguyen.stationery_kimi.service.IProductOptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/api/product-options")
public class ProductOptionManagerRestController {
    @Autowired
    private IProductOptionService iProductOptionService;

    @GetMapping("/get/{productId}")
    public DatatableApiResponse<List<ProductOptionDTO>> get(@PathVariable("productId") Long productId, @RequestParam(required = false) Map<String, Object> query) {
        DatatableApiRequest request = DatatableApiRequest.newInstance(query);
        List<ProductOptionDTO> productOptionDTOs = iProductOptionService.findAllByProductId(productId, request);

        return DatatableApiResponse.<List<ProductOptionDTO>>builder()
                .data(productOptionDTOs.stream().skip(request.getStart()).limit(request.getLength()).toList())
                .draw(request.getDraw())
                .recordsTotal(productOptionDTOs.size())
                .recordsFiltered(productOptionDTOs.size())
                .build();
    }

    @PostMapping("/create/{productId}")
    public DatatableApiResponse<ProductOptionDTO> addProduct(@PathVariable("productId") Long productId, @RequestBody ProductOption productOption) {
        ProductOptionDTO dto = iProductOptionService.add(productId, productOption);
        return DatatableApiResponse.<ProductOptionDTO>builder().data(dto).build();
    }

    @PutMapping("/edit/{id}")
    public DatatableApiResponse<ProductOptionDTO> updateProduct(@PathVariable("id") Long id, @RequestBody ProductOption productOption) {
        ProductOptionDTO dto = iProductOptionService.update(id, productOption);
        return DatatableApiResponse.<ProductOptionDTO>builder().data(dto).build();
    }

    @DeleteMapping("/delete/{id}")
    public DatatableApiResponse<ProductOptionDTO> delete(@PathVariable("id") Long id) {
        ProductOptionDTO result = iProductOptionService.delete(id);
        return DatatableApiResponse.<ProductOptionDTO>builder().data(result).build();
    }
}
