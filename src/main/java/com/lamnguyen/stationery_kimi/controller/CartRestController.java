package com.lamnguyen.stationery_kimi.controller;

import com.lamnguyen.stationery_kimi.dto.CartItemDisplay;
import com.lamnguyen.stationery_kimi.request.AddCartItemRequest;
import com.lamnguyen.stationery_kimi.request.DeleteCartItemRequest;
import com.lamnguyen.stationery_kimi.request.EditQuantityCartItemRequest;
import com.lamnguyen.stationery_kimi.response.ApiResponse;
import com.lamnguyen.stationery_kimi.service.IShoppingCartService;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
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
        return ApiResponse.<EditCartItemResponse>builder().message("Thêm sản phẩm vào giỏ hàng thành công!").data(new EditCartItemResponse(item.getPrice() * item.getQuantity(), cart.stream().mapToInt(CartItemDisplay::getTotalPrice).sum())).build();
    }

    @PostMapping("/decrease")
    public ApiResponse<EditCartItemResponse> decreaseCartItem(HttpSession session, @RequestBody @Valid EditQuantityCartItemRequest request) {
        shoppingCartService.decreaseCartItemQuantity(session, request);
        List<CartItemDisplay> cart = shoppingCartService.loadCart(session);
        CartItemDisplay item = cart.stream().filter(cartItemDisplay -> cartItemDisplay.getCartItemId().equals(request.getCartItemId())).findFirst().orElse(CartItemDisplay.builder().build());
        return ApiResponse.<EditCartItemResponse>builder().message("Giảm số lượng sản phẩm trong giỏ hàng thành công!").data(new EditCartItemResponse(item.getPrice() * item.getQuantity(), cart.stream().mapToInt(CartItemDisplay::getTotalPrice).sum())).build();
    }

    @DeleteMapping("/delete")
    public ApiResponse<Integer> removeCartItem(HttpSession session, @RequestBody DeleteCartItemRequest request) {
        shoppingCartService.deleteCartItem(session, request);
        return ApiResponse.<Integer>builder().message("Xóa sản phẩm khỏi giỏ hàng thành công!")
                .data(shoppingCartService.loadCart(session).stream().mapToInt(CartItemDisplay::getTotalPrice).sum()).build();
    }

    record EditCartItemResponse(Integer totalPriceCartItem, Integer totalPriceCart) {
    }
}

