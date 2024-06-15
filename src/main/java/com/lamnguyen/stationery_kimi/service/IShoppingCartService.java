package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.request.AddCartItemRequest;
import com.lamnguyen.stationery_kimi.request.DeleteCartItemRequest;
import com.lamnguyen.stationery_kimi.request.EditQuantityCartItemRequest;
import jakarta.servlet.http.HttpSession;

public interface IShoppingCartService {
    boolean addCartItem(HttpSession session, AddCartItemRequest request);

    boolean increaseCartItemQuantity(HttpSession session, EditQuantityCartItemRequest request);

    boolean decreaseCartItemQuantity(HttpSession session, EditQuantityCartItemRequest request);

    boolean deleteCartItem(HttpSession session, DeleteCartItemRequest request);
}
