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
@SessionAttributes("cart")
@RequestMapping("/api/cart")
public class CartController {
    @Autowired
    private IShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ApiResponse<Void> addCartItem(HttpSession session, @RequestBody @Valid AddCartItemRequest request) {
        shoppingCartService.addCartItem(session, request);
        return ApiResponse.<Void>builder()
                .message("Thêm sản phẩm vào giỏ hàng thành công!")
                .build();
    }

    @PostMapping("/increase")
    public ApiResponse<Void> increaseCartItem(HttpSession session, @RequestBody @Valid EditQuantityCartItemRequest request) {
        shoppingCartService.increaseCartItemQuantity(session, request);
        return ApiResponse.<Void>builder()
                .message("Thêm sản phẩm vào giỏ hàng thành công!")
                .build();
    }

    @PostMapping("/decrease")
    public ApiResponse<Void> decreaseCartItem(HttpSession session, @RequestBody @Valid EditQuantityCartItemRequest request) {
        shoppingCartService.decreaseCartItemQuantity(session, request);
        return ApiResponse.<Void>builder()
                .message("Giảm số lượng sản phẩm trong giỏ hàng thành công!")
                .build();
    }

    @DeleteMapping("/delete")
    public ApiResponse<Void> removeCartItem(HttpSession session, @RequestBody DeleteCartItemRequest request) {
        shoppingCartService.deleteCartItem(session, request);
        return ApiResponse.<Void>builder()
                .message("Xóa sản phẩm khỏi giỏ hàng thành công!")
                .build();
    }
}