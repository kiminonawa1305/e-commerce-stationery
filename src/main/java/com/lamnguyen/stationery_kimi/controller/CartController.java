package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.request.AddCartItemRequest;
import com.lamnguyen.stationery_kimi.request.DeleteCartItemRequest;
import com.lamnguyen.stationery_kimi.request.EditQuantityCartItemRequest;
import com.lamnguyen.stationery_kimi.response.ApiResponse;
import com.lamnguyen.stationery_kimi.service.IShoppingCartService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController()
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private IShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ApiResponse<Integer> addCartItem(HttpSession session, @RequestBody @Valid AddCartItemRequest request) {
        return ApiResponse.<Integer>builder()
                .message("Thêm sản phẩm vào giỏ hàng thành công!")
                .data(shoppingCartService.addCartItem(session, request))
                .build();
    }

    @PostMapping("/increase")
    public ApiResponse<Integer> increaseCartItem(HttpSession session, @RequestBody @Valid EditQuantityCartItemRequest request) {
        return ApiResponse.<Integer>builder()
                .message("Thêm sản phẩm vào giỏ hàng thành công!")
                .data(shoppingCartService.increaseCartItemQuantity(session, request))
                .build();
    }

    @PostMapping("/decrease")
    public ApiResponse<Integer> decreaseCartItem(HttpSession session, @RequestBody @Valid EditQuantityCartItemRequest request) {
        return ApiResponse.<Integer>builder()
                .message("Giảm số lượng sản phẩm trong giỏ hàng thành công!")
                .data(shoppingCartService.decreaseCartItemQuantity(session, request))
                .build();
    }

    @DeleteMapping("/delete")
    public ApiResponse<Integer> removeCartItem(HttpSession session, @RequestBody DeleteCartItemRequest request) {
        return ApiResponse.<Integer>builder()
                .message("Xóa sản phẩm khỏi giỏ hàng thành công!")
                .data(shoppingCartService.deleteCartItem(session, request))
                .build();
    }
}