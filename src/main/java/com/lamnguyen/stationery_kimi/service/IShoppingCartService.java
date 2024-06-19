package com.lamnguyen.stationery_kimi.service;

import com.lamnguyen.stationery_kimi.dto.Cart;
import com.lamnguyen.stationery_kimi.dto.CartItemDisplay;
import com.lamnguyen.stationery_kimi.entity.Bill;
import com.lamnguyen.stationery_kimi.entity.BillDetail;
import com.lamnguyen.stationery_kimi.request.AddCartItemRequest;
import com.lamnguyen.stationery_kimi.request.DeleteCartItemRequest;
import com.lamnguyen.stationery_kimi.request.EditQuantityCartItemRequest;
import jakarta.servlet.http.HttpSession;

import java.util.List;

public interface IShoppingCartService {
    Integer addCartItem(HttpSession session, AddCartItemRequest request);

    Integer increaseCartItemQuantity(HttpSession session, EditQuantityCartItemRequest request);

    Integer decreaseCartItemQuantity(HttpSession session, EditQuantityCartItemRequest request);

    Integer deleteCartItem(HttpSession session, DeleteCartItemRequest request);

    List<CartItemDisplay> loadCart(HttpSession session);

    Cart getCart(HttpSession session);

    List<BillDetail> getBillDetails(HttpSession session, Bill bill);
}
