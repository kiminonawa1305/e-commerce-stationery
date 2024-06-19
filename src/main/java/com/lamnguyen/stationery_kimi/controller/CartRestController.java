package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.dto.CartItemDisplay;
import com.lamnguyen.stationery_kimi.request.AddCartItemRequest;
import com.lamnguyen.stationery_kimi.request.DeleteCartItemRequest;
import com.lamnguyen.stationery_kimi.request.EditQuantityCartItemRequest;
import com.lamnguyen.stationery_kimi.dto.ApiResponse;
import com.lamnguyen.stationery_kimi.service.IShoppingCartService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import lombok.Builder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/api/cart")
public class CartRestController {
    @Autowired
    private IShoppingCartService shoppingCartService;

    @PostMapping("/add")
    public ApiResponse<Integer> addCartItem(HttpSession session, @RequestBody @Valid AddCartItemRequest request) {
        return ApiResponse.<Integer>builder().message("Thêm sản phẩm vào giỏ hàng thành công!").data(shoppingCartService.addCartItem(session, request)).build();
    }

    @PostMapping("/increase")
    public ApiResponse<EditCartItemResponse> increaseCartItem(HttpSession session, @RequestBody @Valid EditQuantityCartItemRequest request) {
        shoppingCartService.increaseCartItemQuantity(session, request);
        List<CartItemDisplay> cart = shoppingCartService.loadCart(session);
        CartItemDisplay item = cart.stream().filter(cartItemDisplay -> cartItemDisplay.getCartItemId().equals(request.getCartItemId())).findFirst().orElse(CartItemDisplay.builder().build());
        return ApiResponse.<EditCartItemResponse>builder()
                .message("Thêm sản phẩm vào giỏ hàng thành công!")
                .data(EditCartItemResponse.builder()
                        .totalAmount(cart.size())
                        .totalPay(cart.stream().mapToInt(CartItemDisplay::getTotalPay).sum())
                        .totalDiscount(cart.stream().mapToInt(CartItemDisplay::getTotalDiscount).sum())
                        .totalPrice(cart.stream().mapToInt(CartItemDisplay::getTotalPrice).sum())
                        .totalPayItem(item.getTotalPay())
                        .build())
                .build();
    }

    @PostMapping("/decrease")
    public ApiResponse<EditCartItemResponse> decreaseCartItem(HttpSession session, @RequestBody @Valid EditQuantityCartItemRequest request) {
        shoppingCartService.decreaseCartItemQuantity(session, request);
        List<CartItemDisplay> cart = shoppingCartService.loadCart(session);
        CartItemDisplay item = cart.stream().filter(cartItemDisplay -> cartItemDisplay.getCartItemId().equals(request.getCartItemId())).findFirst().orElse(CartItemDisplay.builder().build());
        return ApiResponse.<EditCartItemResponse>builder()
                .message("Giảm số lượng sản phẩm trong giỏ hàng thành công!")
                .data(EditCartItemResponse.builder()
                        .totalAmount(cart.size())
                        .totalPay(cart.stream().mapToInt(CartItemDisplay::getTotalPay).sum())
                        .totalDiscount(cart.stream().mapToInt(CartItemDisplay::getTotalDiscount).sum())
                        .totalPrice(cart.stream().mapToInt(CartItemDisplay::getTotalPrice).sum())
                        .totalPayItem(item.getTotalPay())
                        .build())
                .build();
    }

    @DeleteMapping("/delete")
    public ApiResponse<EditCartItemResponse> removeCartItem(HttpSession session, @RequestBody DeleteCartItemRequest request) {
        shoppingCartService.deleteCartItem(session, request);
        List<CartItemDisplay> cart = shoppingCartService.loadCart(session);
        return ApiResponse.<EditCartItemResponse>builder().message("Xóa sản phẩm khỏi giỏ hàng thành công!")
                .data(EditCartItemResponse.builder()
                        .totalAmount(cart.size())
                        .totalPay(cart.stream().mapToInt(CartItemDisplay::getTotalPay).sum())
                        .totalDiscount(cart.stream().mapToInt(CartItemDisplay::getTotalDiscount).sum())
                        .totalPrice(cart.stream().mapToInt(CartItemDisplay::getTotalPrice).sum())
                        .build())
                .build();
    }

    @Builder
    record EditCartItemResponse(Integer totalPrice,
                                Integer totalDiscount,
                                Integer totalPay,
                                Integer totalAmount,
                                Integer totalPayItem) {
    }
}

