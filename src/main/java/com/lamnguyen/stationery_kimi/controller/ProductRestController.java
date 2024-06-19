package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.dto.ProductDTO;
import com.lamnguyen.stationery_kimi.dto.ProductDisplayDTO;
import com.lamnguyen.stationery_kimi.exception.ApplicationException;
import com.lamnguyen.stationery_kimi.exception.ErrorCode;
import com.lamnguyen.stationery_kimi.dto.ApiResponse;
import com.lamnguyen.stationery_kimi.service.ICategoryService;
import com.lamnguyen.stationery_kimi.service.IProductService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/products")
public class ProductRestController {
    private static final Logger log = LoggerFactory.getLogger(ProductRestController.class);
    @Autowired
    private IProductService productService;
    private ICategoryService iCategoryService;

    @GetMapping("/page-{page}")
    public ApiResponse<List<ProductDisplayDTO>> findAllByLockFalse(@PathVariable("page") Integer page) {
        List<ProductDisplayDTO> result = productService.findAllByLockFalse(20, page);
        if (result.isEmpty()) throw new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND);
        return ApiResponse.<List<ProductDisplayDTO>>builder()
                .message("Thành công!")
                .data(result)
                .build();
    }

    @GetMapping("/booth/{categoryId}")
    public ApiResponse<List<ProductDisplayDTO>> bootByPage(@RequestParam("page") Optional<Integer> page,
                                                           @RequestParam("brands") Optional<String[]> brands,
                                                           @RequestParam("priceRange") Optional<String> priceRange,
                                                           @RequestParam("sortByName") Optional<String> sortByName,
                                                           @RequestParam("sortByPrice") Optional<String> sortByPrice,
                                                           @RequestParam("sortByNewProduct") Optional<String> sortByNewProduct,
                                                           @PathVariable("categoryId") Long categoryId) {
        List<ProductDisplayDTO> products = productService.findByCategory(categoryId, 99999, 0);
        if (brands.isPresent())
            products = products.stream().filter(product -> Arrays.stream(brands.get()).anyMatch(product.getBrand().toLowerCase()::equalsIgnoreCase)).toList();

        if (priceRange.isPresent()) {
            String[] minMax = priceRange.get().split("-");
            products = products.stream().filter(product -> product.getPrice() >= Integer.parseInt(minMax[0]) && product.getPrice() <= Integer.parseInt(minMax[1])).toList();
        }

        if (products.size() != 1) {
            if (sortByName.isPresent())
                if (sortByName.get().equalsIgnoreCase("asc"))
                    products.sort(Comparator.comparing(ProductDTO::getName));
                else if (sortByName.get().equalsIgnoreCase("desc"))
                    products.sort((product1, product2) -> product2.getName().toLowerCase().compareTo(product1.getName().toLowerCase()));

            if (sortByPrice.isPresent())
                if (sortByPrice.get().equalsIgnoreCase("asc"))
                    products.sort(Comparator.comparing(ProductDTO::getPrice));
                else if (sortByPrice.get().equalsIgnoreCase("desc"))
                    products.sort((product1, product2) -> product2.getPrice().compareTo(product1.getPrice()));
        }

        if (sortByNewProduct.isPresent())
            products = products.stream().filter(ProductDTO::getProductNew).toList();

        if (page.isPresent())
            products = products.stream().skip(page.get() * 8).limit(8).toList();

        return ApiResponse.<List<ProductDisplayDTO>>builder()
                .code(202)
                .message("success")
                .data(products)
                .build();
    }
}
