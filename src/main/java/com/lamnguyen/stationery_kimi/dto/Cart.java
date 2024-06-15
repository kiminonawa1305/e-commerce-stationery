package com.lamnguyen.stationery_kimi.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashMap;
import java.util.Map;

@Data
@Builder
@AllArgsConstructor
public class Cart {
    private Map<String, CartItem> cartItems;


    public Cart() {
        this.cartItems = new HashMap<String, CartItem>();
    }

    public void addCartItem(CartItem cartItem) {
        cartItems.put(cartItem.getCartItemId(), cartItem);
    }

    public void deleteCartItem(String cartItemId) {
        cartItems.remove(cartItemId);
    }

    public CartItem findCartItem(String cartItemId) {
        return cartItems.get(cartItemId);
    }

    public Long getProductId(String cartItemId) {
        return Long.parseLong(cartItemId.split("-")[0]);
    }

    public Long getProductOptionId(String cartItemId) {
        return Long.parseLong(cartItemId.split("-")[1]);
    }
}


