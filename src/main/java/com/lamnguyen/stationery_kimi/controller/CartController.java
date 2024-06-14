package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.exception.ApplicationException;
import com.lamnguyen.stationery_kimi.exception.ErrorCode;
import com.lamnguyen.stationery_kimi.response.ApiResponse;
import com.lamnguyen.stationery_kimi.service.IShoppingCartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController()
@SessionAttributes("cart")
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private IShoppingCartService shoppingCartService;

    @PostMapping("/add/{id}")
    public ApiResponse<Void> addProductToCart(@RequestBody Map<String, Object> prams, @PathVariable("id") Long id) {
        Integer quantity = (Integer) prams.get("quantity");
        if (id == null || quantity == null) throw new ApplicationException(ErrorCode.PRODUCT_NOT_FOUND);
        shoppingCartService.addProduct(id, quantity);
        return ApiResponse.<Void>builder()
                .message("Thêm sản phẩm vào giỏ hàng thành công!")
                .build();
    }
}
